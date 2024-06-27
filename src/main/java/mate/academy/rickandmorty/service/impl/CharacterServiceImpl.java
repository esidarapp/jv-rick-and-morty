package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exceptions.DataProcessingException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClient;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private static final int CHARACTERS_COUNT = 826;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharacterClient characterClient;

    @Override
    public CharacterDto save(CharacterDataDto characterDataDto) {
        return characterMapper.toDto(
                characterRepository.save(
                        characterMapper.toModel(characterDataDto)));
    }

    @Override
    public CharacterDto findById(Long id) {
        Character character = characterRepository
                .findById(id)
                .orElseThrow(() -> new DataProcessingException(
                        "Can`t find character by id: " + id));
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterDto> findCharactersByName(String name) {
        return characterRepository.findCharacterByNameContainingIgnoreCase(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @Override
    public CharacterDto findByRandomId() {
        Random random = new Random();
        return findById(random.nextLong(CHARACTERS_COUNT) + 1);
    }

    @PostConstruct
    public void init() {
        var characterList = characterClient.getCharacterDataFromApi();
        characterRepository.saveAll(characterMapper.toCharacterList(characterList));
    }
}
