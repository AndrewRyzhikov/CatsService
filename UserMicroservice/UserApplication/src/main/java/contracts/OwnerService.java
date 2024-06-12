package contracts;


import dtos.owner.OwnerDto;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    void add(OwnerDto ownerDto);

    Optional<OwnerDto> get(Long id);

    Optional<List<OwnerDto>> getAll();

    void update(OwnerDto ownerDto);

    void delete(Long id);
}
