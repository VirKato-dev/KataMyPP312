package my.virkato.kata312.services;

import my.virkato.kata312.dao.UserDao;
import my.virkato.kata312.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

    private final UserDao dao;

    public UserService(UserDao userDao) {
        dao = userDao;
    }

    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        return dao.findUserEntityByUsername(username);
    }

    public UserDao getDao() {
        return dao;
    }

    @Transactional
    public UserEntity createOrUpdate(UserEntity user) {
        return dao.save(user);
    }

}
