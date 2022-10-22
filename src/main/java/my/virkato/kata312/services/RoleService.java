package my.virkato.kata312.services;

import my.virkato.kata312.entities.RoleEntity;
import my.virkato.kata312.repositories.RoleDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService {

    private final RoleDao dao;

    public RoleService(RoleDao roleDao) {
        dao = roleDao;
    }

    @Transactional
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

    public ArrayList<RoleEntity> getAvailableRoles() {
        ArrayList<RoleEntity> roles = new ArrayList<>();
        dao.findAll().forEach(roles::add);
        return roles;
    }
}
