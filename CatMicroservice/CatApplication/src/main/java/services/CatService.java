package services;


import cat.CatModel;

import java.util.List;
import java.util.Optional;

public interface CatService {
    void add(CatModel catModel);

    void addFriend(Long catId, Long friendId);

    Optional<CatModel> getById(Long id);

    Optional<CatModel> getByIdAndOwnerId(Long catId, Long ownerId);

    Optional<List<CatModel>> getAll(String color, String breed, Long ownerId);

    void update(CatModel catModel);

    void delete(Long id);
}
