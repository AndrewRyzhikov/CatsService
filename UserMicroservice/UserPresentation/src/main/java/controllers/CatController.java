package controllers;

import contracts.CatService;
import dtos.cat.CatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cats")
@ComponentScan(basePackages = {"application"})
public class CatController {
    private final CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @PostMapping
    public void add(@RequestBody CatDto catDto) {
        catService.add(catDto);
    }

    @PostMapping("/{catId}/friends/{friendId}")
    public void addFriend(@PathVariable(name = "catId") Long catId, @PathVariable(name = "friendId") Long friendId) {
        catService.addFriend(catId, friendId);
    }

    @GetMapping("/{id}")
    public Optional<CatDto> get(@PathVariable(name = "id") Long id) {
        return catService.get(id);
    }

    @GetMapping
    public Optional<List<CatDto>> getAll(@RequestParam(name = "color", required = false) String color,
                                         @RequestParam(name = "breed", required = false) String breed) {
        return catService.getAll(breed, color);
    }

    @PutMapping
    public void update(@RequestBody CatDto catDto) {
        catService.update(catDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        catService.delete(id);
    }
}

