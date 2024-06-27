package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character management", description = "Endpoints for managing characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("/character")
public class CharactersController {
    private static final int CHARACTERS_COUNT = 826;

    private final CharacterService characterService;

    @Operation(summary = "Get character by random id",
            description = "Returns a character by random id")
    @GetMapping("/random")
    public CharacterDto findCharacterByRandomId() {
        return characterService.findByRandomId();
    }

    @GetMapping("/searchByName")
    @Operation(summary = "Search for characters by part of their name",
            description = "Returns a list of all characters whose name contains search name")
    public List<CharacterDto> searchByName(@RequestParam(defaultValue = "rick") String name) {
        return characterService.findCharactersByName(name);
    }
}
