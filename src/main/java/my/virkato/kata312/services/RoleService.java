package my.virkato.kata312.services;

import my.virkato.kata312.entities.RoleEntity;
import my.virkato.kata312.repositories.RoleRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class RoleService {

    private final RoleRepo repo;

    public RoleService(RoleRepo roleRepo) {
        repo = roleRepo;
    }

    @Transactional
    public RoleEntity createRole(String role) {
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        RoleEntity entity = repo.findRoleEntityByAuthority(role);
        if (entity == null) {
            entity = repo.save(new RoleEntity(role));
        }
        return entity;
    }

    public ArrayList<RoleEntity> getAvailableRoles() {
        ArrayList<RoleEntity> roles = new ArrayList<>();
        repo.findAll().forEach(roles::add);
        return roles;
    }
}
