package services;

import owner.OwnerModel;

import java.util.List;
import java.util.Optional;

public interface OwnerService
{
    void add(OwnerModel ownerModel);

    Optional<OwnerModel> get(Long id);

    Optional<List<OwnerModel>> getAll();

    void update(OwnerModel catModel);

    void delete(Long id);
}
