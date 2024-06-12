package client;

import dtos.owner.OwnerDto;

import java.util.List;
import java.util.Optional;

public interface OwnerClient {
    Optional<OwnerDto> getById(Long id);
    Optional<List<OwnerDto>> getAll();
}
