package my.virkato.kata312.controllers;

import my.virkato.kata312.entities.UserEntity;
import my.virkato.kata312.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/users")
public class UserRestControllers {

    private final UserService userService;

    public UserRestControllers(UserService userService) {
        this.userService = userService;
    }

    // CREATE

    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity user) {
        return new ResponseEntity<>(userService.createOrUpdate(user), HttpStatus.OK);
    }

    // READ

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<UserEntity>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    // UPDATE

    @PatchMapping
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity user) {
        userService.createOrUpdate(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

}
