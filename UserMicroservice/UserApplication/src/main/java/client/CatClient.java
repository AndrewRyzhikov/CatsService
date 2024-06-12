package client;


import dtos.cat.CatDto;

import java.util.List;
import java.util.Optional;

public interface CatClient {
    Optional<CatDto> getById(Long id);
    Optional<CatDto> getByIdAndOwnerId(Long id, Long ownerId);
    Optional<List<CatDto>> getAll(String breed, String color);
    Optional<List<CatDto>> getAllByOwnerId(String breed, String color, Long ownerId);
}
