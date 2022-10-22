package my.virkato.kata312.services;

import my.virkato.kata312.entities.UserEntity;
import my.virkato.kata312.repositories.UserRepo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;


@Service
public class UserService implements UserDetailsService {

    private final UserRepo repo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        repo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findUserEntityByUsername(username);
    }

    @Transactional
    public void createOrUpdate(UserEntity user) {
        if (user.getId() != null) {
            UserEntity oldUser = get(user.getId());
            user.setUsername(oldUser.getUsername());
            if (oldUser.getPassword().equals(user.getPassword()) ||
                    "".equals(user.getPassword())) {
                user.setPassword(oldUser.getPassword());
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        repo.save(user);
    }

    /***
     * Получить всех пользователей
     * @return список
     */
    public Collection<UserEntity> getList() {
        ArrayList<UserEntity> list = new ArrayList<>();
        repo.findAll().forEach(list::add);
        return list;
    }

    /***
     * Получить пользователя по ID
     * @param id ID пользователя
     * @return пользователь либо null
     */
    public UserEntity get(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}
