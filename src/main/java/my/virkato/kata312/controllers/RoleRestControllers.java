package my.virkato.kata312.controllers;

import my.virkato.kata312.entities.RoleEntity;
import my.virkato.kata312.entities.UserEntity;
import my.virkato.kata312.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/roles")
public class RoleRestControllers {

    private final RoleService roleService;

    public RoleRestControllers(RoleService roleService) {
        this.roleService = roleService;
    }

    // CREATE

    @PostMapping
    public ResponseEntity<RoleEntity> create(@RequestBody RoleEntity role) {
        return new ResponseEntity<>(roleService.create(role.toString()), HttpStatus.OK);
    }

    // READ

    @GetMapping("/all")
    public ResponseEntity<Collection<RoleEntity>> getAll() {
        return new ResponseEntity<>(roleService.getAll(), HttpStatus.OK);
    }

    // UPDATE

    // DELETE

}
