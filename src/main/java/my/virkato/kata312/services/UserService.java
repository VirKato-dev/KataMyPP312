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

    // CREATE
    // UPDATE

    /***
     * Создать или обновить данные о пользователе
     * @param user новые данные
     * @return обновлённый/созданный пользователь
     */
    @Transactional
    public UserEntity createOrUpdate(UserEntity user) {
        if (user.getId() != null) {
            UserEntity oldUser = get(user.getId());
            user.setUsername(oldUser.getUsername());
            if (oldUser.getPassword().equals(user.getPassword())
                    || "".equals(user.getPassword())) {
                user.setPassword(oldUser.getPassword());
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return repo.save(user);
    }

    // READ

    /***
     * Получить всех пользователей
     * @return список
     */
    public Collection<UserEntity> getAll() {
        ArrayList<UserEntity> list = new ArrayList<>();
        repo.findAll().forEach(list::add);
        return list;
    }

    /***
     * Получить пользователя по ID
     * @param id идентификатор пользователя в базе
     * @return пользователь либо null
     */
    public UserEntity get(Long id) {
        return repo.findById(id).orElse(null);
    }

    /***
     * Получить пользователя по его логину
     * @param username the username identifying the user whose data is required.
     * @return пользователь либо null
     * @throws UsernameNotFoundException
     */
    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findUserEntityByUsername(username);
    }

    // DELETE

    /***
     * Удалить по ID
     * @param id идентификатор пользователя в базе
     */
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
