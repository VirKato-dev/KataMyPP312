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

    // CREATE

    /***
     * Создать роль если её ещё нет в базе
     * @param role название роли
     * @return роль сохранённая в базе
     */
    @Transactional
    public RoleEntity create(String role) {
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        RoleEntity entity = repo.findRoleEntityByAuthority(role);
        if (entity == null) {
            entity = repo.save(new RoleEntity(role));
        }
        return entity;
    }

    // READ

    /***
     * Получить все известные роли
     * @return список всех ролей в базе
     */
    public ArrayList<RoleEntity> getAll() {
        ArrayList<RoleEntity> roles = new ArrayList<>();
        repo.findAll().forEach(roles::add);
        return roles;
    }

    public RoleEntity get(Long id) {
        return repo.findById(id).orElse(null);
    }

    // UPDATE
    // DELETE

}
