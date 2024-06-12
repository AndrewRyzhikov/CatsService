package dao;

import entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerDao extends JpaRepository<OwnerEntity, Long> {
    @Modifying
    @Query(value = "UPDATE Cats SET owner_id = :ownerId WHERE id = :catId", nativeQuery = true)
    void addCat(@Param("ownerId") Long ownerId, @Param("catId") Long catId);
}
