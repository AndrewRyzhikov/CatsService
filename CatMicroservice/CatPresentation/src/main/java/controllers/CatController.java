package controllers;


import dtos.cat.CatDto;
import mapper.CatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import services.CatService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cats")
@ComponentScan(basePackages = "services")
public class CatController {
    private final CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/{id}/{ownerId}")
    public Optional<CatDto> getByIdAndOwnerId(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "ownerId") Long ownerId) {
        return catService.getByIdAndOwnerId(id, ownerId).map(CatMapper::modelToDto);
    }

    @GetMapping("/admin/{id}")
    public Optional<CatDto> getById(@PathVariable(name = "id") Long id) {
        return catService.getById(id).map(CatMapper::modelToDto);
    }

    @GetMapping("/admin")
    public Optional<List<CatDto>> getAll(
            @RequestParam(name = "color", required = false) String color,
            @RequestParam(name = "breed", required = false) String breed) {
        return catService.getAll(breed, color, null).map(catModels -> catModels
                .stream()
                .map(CatMapper::modelToDto)
                .toList());
    }

    @GetMapping("/{ownerId}")
    public Optional<List<CatDto>> getAll(
            @RequestParam(name = "color", required = false) String color,
            @RequestParam(name = "color", required = false) String breed,
            @PathVariable(name = "ownerId") Long ownerId) {

        return catService.getAll(breed, color, ownerId).map(catModels -> catModels
                .stream()
                .map(CatMapper::modelToDto)
                .toList());
    }
}