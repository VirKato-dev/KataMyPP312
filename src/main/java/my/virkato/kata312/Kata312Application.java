package my.virkato.kata312;

import my.virkato.kata312.entities.RoleEntity;
import my.virkato.kata312.entities.UserEntity;
import my.virkato.kata312.services.RoleService;
import my.virkato.kata312.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Kata312Application {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public Kata312Application(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(Kata312Application.class, args);
    }

    @PostConstruct
    private void init() {
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleService.createRole("ADMIN"));
        roles.add(roleService.createRole("USER"));

        UserEntity user = new UserEntity();
        if (userService.loadUserByUsername("my@mail.ru") == null) {
            user.setUsername("my@mail.ru");
            user.setPassword(passwordEncoder.encode("0000"));
            user.setAge(43);
            user.setFirstName("Vir");
            user.setLastName("Kato");
            user.setRoles(roles);
            userService.createOrUpdate(user);
        }
    }

}
