package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterDataDto;
import mate.academy.rickandmorty.dto.external.CharactersResponseDataDto;
import mate.academy.rickandmorty.exceptions.DataProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterClient {
    @Value("${rick-and-morty.url}")
    private String baseUrl;
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public List<CharacterDataDto> getCharacterDataFromApi() {
        List<CharacterDataDto> characterDataDtos = new ArrayList<>();
        int page = 1;
        try {
            while (true) {
                String url = baseUrl.formatted(page);
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(url))
                        .build();

                HttpResponse<String> response = httpClient.send(
                        httpRequest,
                        HttpResponse.BodyHandlers.ofString()
                );
                CharactersResponseDataDto dataDto = objectMapper.readValue(
                        response.body(),
                        CharactersResponseDataDto.class
                );
                characterDataDtos.addAll(dataDto.characters());
                if (dataDto.info().getNext() == null) {
                    break;
                }
                page++;
            }
        } catch (IOException | InterruptedException e) {
            throw new DataProcessingException("Failed to fetch character information", e);
        }
        return characterDataDtos;
    }
}
