package dtos.owner;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record OwnerDto(Long id, String name, LocalDate birthDate) {
}
