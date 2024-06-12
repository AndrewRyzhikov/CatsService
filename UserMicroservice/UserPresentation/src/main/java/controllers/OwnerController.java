package controllers;

import config.security.UserDetailsImpl;
import contracts.OwnerService;
import dtos.owner.OwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import user.Role;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/owner")
@ComponentScan(basePackages = "application")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void add(@RequestBody OwnerDto ownerDto) {
        ownerService.add(ownerDto);
    }

    @GetMapping("/{id}")
    public Optional<OwnerDto> get(@PathVariable(name = "id") Long id) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails.getRole() == Role.ROLE_ADMIN || Objects.equals(userDetails.getOwnerId(), id)) {
            return ownerService.get(id);
        }

        return Optional.empty();
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Optional<List<OwnerDto>> getAll() {
        return ownerService.getAll();
    }

    @PutMapping
    public void update(@RequestBody OwnerDto ownerDto) {
        ownerService.update(ownerDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable(name = "id") Long id) {
        ownerService.delete(id);
    }
}
