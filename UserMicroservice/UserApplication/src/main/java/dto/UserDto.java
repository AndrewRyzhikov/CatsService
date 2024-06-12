package dto;

import user.Role;

public record UserDto(Long id, String name, String password, Role role, Long ownerId) {
}
