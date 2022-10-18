package my.virkato.kata312.controllers;

import my.virkato.kata312.entities.UserEntity;
import my.virkato.kata312.services.RoleService;
import my.virkato.kata312.services.UserService;
import org.springframework.data.repository.query.Param;
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

    /***
     * Объект для возвращения данных из формы (Thymeleaf) в контроллер
     * @return возвращает новый экземпляр в Thymeleaf для заполнения данными
     */
    @ModelAttribute(name = "newUser")
    public UserEntity newUser() {
        return new UserEntity();
    }

    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(params = "logout")
    public String logout() {
        return "redirect:/logout";
    }

    //--- CREATE

    /***
     * Подготовить объект User для сохранения в базу
     */
    @GetMapping("/new")
    public String createForm(@ModelAttribute("user") UserEntity user) {
        return "admin/new";
    }

    /***
     * Сохранить в базу
     */
    @PostMapping
    public String create(@ModelAttribute("newUser") UserEntity newUser, Model model) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        if (userService.loadUserByUsername(newUser.getUsername()) == null) {
            userService.createOrUpdate(newUser);
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
        return "pages/admin";
    }

    /***
     * Получить одного пользователя
     */
    @GetMapping("/{id}")
    public String read(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("user", userService.get(id));
        return "user/profile";
    }

    //--- UPDATE

    /***
     * Подготовить изменения для объекта User
     */
    @GetMapping("/{id}/edit")
    public String editForm(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("user", userService.get(id));
        return "admin/edit";
    }

    /***
     * Сохранить изменённого пользователя
     */
    @PatchMapping()
    public String edit(@ModelAttribute("user") UserEntity user) {
        UserEntity oldUser = userService.get(user.getId());
        user.setUsername(oldUser.getUsername());
        user.setPassword(oldUser.getPassword());
        user.setRoles(oldUser.getRoles());
        userService.createOrUpdate(user);
        return "redirect:/admin";
    }

    //--- DELETE

    /***
     * Удалить пользователя (подготовки объекта User не требуется)
     */
    @DeleteMapping()
    public String delete(Model model, @Param("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}
