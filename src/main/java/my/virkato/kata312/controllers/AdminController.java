package my.virkato.kata312.controllers;

import my.virkato.kata312.entities.UserEntity;
import my.virkato.kata312.services.RoleService;
import my.virkato.kata312.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    //--- CREATE

    /***
     * Сохранить в базу
     */
    @PostMapping
    public String create(@ModelAttribute("newUser") UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userService.loadUserByUsername(user.getUsername()) == null) {
            userService.createOrUpdate(user);
            return "redirect:/admin";
        } else {
            return "redirect:/admin/new?error=login is exists";
        }
    }

    //--- READ

    /***
     * Получить всех пользователей и все известные роли
     */
    @GetMapping
    public String index(Model model, Principal principal) {
        model.addAttribute("roles", roleService.getAvailableRoles());
        model.addAttribute("users", userService.getList());
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        model.addAttribute("newUser", new UserEntity());
        return "pages/admin";
    }

    //--- UPDATE

    /***
     * Сохранить изменённого пользователя
     */
    @PatchMapping()
    public String edit(@RequestParam(name = "id") Long id, @RequestParam(name = "username") String username) {
//        UserEntity user = new UserEntity(model);
//        UserEntity oldUser = userService.get(user.getId());
//        user.setUsername(oldUser.getUsername());
//        if (!oldUser.getPassword().equals(user.getPassword()) && !"".equals(user.getPassword())) {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//        } else {
//            user.setPassword(oldUser.getPassword());
//        }
//        user.setRoles(oldUser.getRoles());
//        userService.createOrUpdate(user);
        return "redirect:/admin";
    }

    //--- DELETE

    /***
     * Удалить пользователя (подготовки объекта User не требуется)
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}
