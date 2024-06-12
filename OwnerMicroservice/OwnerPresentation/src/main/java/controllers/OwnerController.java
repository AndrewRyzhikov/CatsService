package controllers;

import dtos.owner.OwnerDto;
import mapper.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import services.OwnerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/owner")
@ComponentScan(basePackages = "services")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/{id}")
    public Optional<OwnerDto> get(@PathVariable(name = "id") Long id) {
        return ownerService.get(id).map(OwnerMapper::modelToDto);
    }

    @GetMapping()
    public Optional<List<OwnerDto>> getAll() {
        return ownerService.getAll().map(catModels -> catModels
                .stream()
                .map(OwnerMapper::modelToDto)
                .toList());
    }
}
