package my.virkato.kata312.services;

import my.virkato.kata312.entities.RoleEntity;
import my.virkato.kata312.repositories.RoleDao;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleDao dao;

    public RoleService(RoleDao roleDao) {
        dao = roleDao;
    }

    public RoleEntity createRole(String role) {
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        RoleEntity entity = dao.findRoleEntityByAuthority(role);
        if (entity == null) {
            entity = dao.save(new RoleEntity(role));
        }
        return entity;
    }
}
