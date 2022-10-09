package my.virkato.kata312.repositories;

import my.virkato.kata312.entities.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<RoleEntity, Long> {
    RoleEntity findRoleEntityByAuthority(String authority);
}
