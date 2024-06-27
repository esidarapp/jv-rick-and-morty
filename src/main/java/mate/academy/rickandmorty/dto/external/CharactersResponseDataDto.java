package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record CharactersResponseDataDto(
        @JsonProperty("results")
        List<CharacterDataDto> characters,
        @JsonProperty("info")
        PageInfo info
){
}
