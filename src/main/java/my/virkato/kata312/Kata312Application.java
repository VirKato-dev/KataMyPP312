package my.virkato.kata312;

import my.virkato.kata312.entities.RoleEntity;
import my.virkato.kata312.entities.UserEntity;
import my.virkato.kata312.services.RoleService;
import my.virkato.kata312.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Set;

@SpringBootApplication
public class Kata312Application {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(Kata312Application.class, args);
    }

    @PostConstruct
    public void init() {
        RoleEntity role1 = roleService.createRole("ADMIN");
        RoleEntity role2 = roleService.createRole("USER");
        userService.createOrUpdate(new UserEntity("my@mail.ru",
                passwordEncoder.encode("0000"), "Admin",
                "+79008006050", Set.of(role1, role2)));
    }
}
