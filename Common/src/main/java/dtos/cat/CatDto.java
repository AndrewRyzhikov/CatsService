package dtos.cat;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CatDto(Long id, String name, LocalDate birthDate, String breed, String color, Long ownerId) {
}
