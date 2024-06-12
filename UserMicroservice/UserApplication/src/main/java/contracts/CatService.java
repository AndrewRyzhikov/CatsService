package contracts;

import dtos.cat.CatDto;

import java.util.List;
import java.util.Optional;

public interface CatService {
    void add(CatDto catDto);

    void addFriend(Long catId, Long friendId);

    Optional<CatDto> get(Long id);

    Optional<List<CatDto>> getAll(String breed, String color);

    void update(CatDto catDto);

    void delete(Long id);
}
