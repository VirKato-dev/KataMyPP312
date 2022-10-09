package my.virkato.kata312.repositories;

import my.virkato.kata312.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<UserEntity, Long> {
    UserEntity findUserEntityByUsername(String username);
}
