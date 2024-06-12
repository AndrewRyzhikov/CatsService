package dao;

import entity.CatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatDao extends JpaRepository<CatEntity, Long> {
    @Modifying
    @Query(value = "INSERT INTO Cat_Friends (cat_id, friend_id) VALUES (:catId, :friendId)", nativeQuery = true)
    void addFriend(@Param("catId") Long catId, @Param("friendId") Long friendId);

    Optional<CatEntity> getByOwnerId(Long ownerId);

    Optional<CatEntity> findAllByOwnerId(Long ownerId);
}
