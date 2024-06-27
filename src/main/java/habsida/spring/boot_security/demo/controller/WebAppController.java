package habsida.spring.boot_security.demo.controller;

import habsida.spring.boot_security.demo.model.Role;
import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.repository.RoleRepository;
import habsida.spring.boot_security.demo.repository.UserRepository;
import habsida.spring.boot_security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class WebAppController {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public WebAppController(UserService userService, RoleRepository roleRepository, UserRepository userRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;

    }

//    @GetMapping("/register")
//    public String showRegistration() {
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String registerAcc(@ModelAttribute("user") @Valid User user, @ModelAttribute("role") Role role, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            return "register";
//        }
//
//        user.setRole(role);
//        userRepository.save(user);
//        userService.createAcc(user.getIdUser(), role.getIdRole());
//        return "redirect:/login";
//
//    }
//
//    @GetMapping("/login")
//    public String loginPage() {
//        return "login";
//
//    }
//
//    @PostMapping("/login")
//    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
//        User user = userService.loginUser(email, password);
//        if (user == null) {
//            session.setAttribute("currentUser", user);
//            return "redirect:/";
//.
//        } else {
//            model.addAttribute("error", "Invalid email or password");
//            return "login";
//
//        }
//
//    }
//
//    @GetMapping("/logout")
//    public String logoutPage() {
//        return "logout";
//
//    }

    @GetMapping("/user")
    public String userHomePage(Model model, Principal principal) {
//        try {
//            List<User> users = userService.getAllUsers();
//            model.addAttribute("users", users);
//        } catch (Exception e) {
//            model.addAttribute("users", null);
//        }

        User loggedUser = userRepository.findByUsername(principal.getName());
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("roles", roleRepository.findAll());

        return "user/user-home";

    }

    @GetMapping("/admin")
    public String adminHomePage(Model model, Principal principal) {
        User loggedUser = userRepository.findByUsername(principal.getName());
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("roles", roleRepository.findAll());

        try {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
        } catch (Exception e) {
            model.addAttribute("users", null);
        }

        model.addAttribute("user", new User());

        return "admin/admin-home";
    }

    @GetMapping("/admin/add")
    public String showAddUserForm(Model model, Principal principal) {
        User loggedUser = userRepository.findByUsername(principal.getName());
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/add-user-form";

    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/add-user-form";
        }

//        Set<Role> roles = roleId.stream()
//                        .map(roleRepository::findById)
//                                .filter(Optional::isPresent)
//                                        .map(Optional::get)
//                                                .collect(Collectors.toSet());

        Set<Role> roles = user.getRoles();
        user.setRoles(roles);
        userService.saveOrUpdateUser(user);
        return "redirect:/admin";

    }

    @GetMapping(value = "admin/update/{id}")
    public String showUserUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/admin-home";
    }

    @PostMapping("admin/update/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
        userService.saveOrUpdateUser(user);
        return "redirect:/admin";

    }

    @PostMapping(value = "admin/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/admin";

    }

    @GetMapping("/admin/list")
    public String adminListView(Model model) {
        try {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
        } catch (Exception e) {
            model.addAttribute("users", null);
        }

        return "admin/admin-list-view";

    }

}
