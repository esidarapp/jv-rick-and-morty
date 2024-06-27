package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    CharacterDto save(CharacterDataDto characterDataDto);

    CharacterDto findById(Long id);

    List<CharacterDto> findCharactersByName(String name);
}
