package my.virkato.kata312.controllers;

import my.virkato.kata312.entities.RoleEntity;
import my.virkato.kata312.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
public class RoleRestControllers {

    private final RoleService roleService;

    public RoleRestControllers(RoleService roleService) {
        this.roleService = roleService;
    }

    // READ

    @GetMapping("/roles")
    public ResponseEntity<Collection<RoleEntity>> getAll() {
        return new ResponseEntity<>(roleService.getAll(), HttpStatus.OK);
    }

    // CREATE

    // UPDATE

    // DELETE

}
