package my.virkato.kata312.controllers;

import my.virkato.kata312.entities.RoleEntity;
import my.virkato.kata312.entities.UserEntity;
import my.virkato.kata312.services.RoleService;
import my.virkato.kata312.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
public class MyRestControllers {

    private final UserService userService;
    private final RoleService roleService;

    public MyRestControllers(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    // CREATE

    @PostMapping("/admin")
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity user) {
        return new ResponseEntity<>(userService.createOrUpdate(user), HttpStatus.OK);
    }

    // READ

    @GetMapping("/user")
    public ResponseEntity<UserEntity> get(Authentication auth) {
        return new ResponseEntity<>(userService.loadUserByUsername(auth.getName()), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<Collection<RoleEntity>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAll(), HttpStatus.OK);
    }


    @GetMapping("/admin/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable Long id) {
        return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<Collection<UserEntity>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    // UPDATE

    @PatchMapping("/admin")
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity user) {
        return new ResponseEntity<>(userService.createOrUpdate(user), HttpStatus.OK);
    }

    // DELETE

    @DeleteMapping("/admin")
    public ResponseEntity<Void> delete(@RequestBody UserEntity user) {
        userService.delete(user.getId());
        return ResponseEntity.ok().build();
    }

}