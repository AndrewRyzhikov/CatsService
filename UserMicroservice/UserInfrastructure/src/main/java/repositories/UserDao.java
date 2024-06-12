package repositories;

import entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(@Param("name")String name);
}
