package habsida.spring.boot_security.demo.controller;

import habsida.spring.boot_security.demo.model.Role;
import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.repository.RoleRepository;
import habsida.spring.boot_security.demo.repository.UserRepository;
import habsida.spring.boot_security.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private static final Logger logger = LoggerFactory.getLogger(WebAppController.class);

    @Autowired
    public WebAppController(UserService userService, RoleRepository roleRepository, UserRepository userRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public String userHomePage(Model model, Principal principal) {
        User loggedUser = userRepository.findByUsername(principal.getName());
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("roles", roleRepository.findAll());

        return "user/user-home";

    }

    @GetMapping("/admin")
    public String adminHomePage(Model model,
                                Principal principal,
                                @ModelAttribute("user") @Valid User user) {
        User loggedUser = userRepository.findByUsername(principal.getName());
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("roles", roleRepository.findAll());

        try {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
        } catch (Exception e) {
            model.addAttribute("users", null);
        }

        model.addAttribute("userAdd", new User());
        model.addAttribute("userToUpdate", userRepository.findUserById(user.getIdUser()));

        return "admin/admin-home";

    }

    @PostMapping("/admin/add")
    public String add(@ModelAttribute("user") @Valid User user,
                      BindingResult bindingResult,
                      @RequestParam("roles") Set<Role> roles) {
        if (bindingResult.hasErrors()) {
            logger.error("Error creating new user {}", user);
        }

        user.setRoles(roles);
        userService.saveUser(user);
        logger.info("new user was created {}", user);
        return "redirect:/admin";

    }

    @GetMapping(value = "/admin/update/{id}")
    public String getUserForUpdate(@PathVariable Long id, Model model) {
        User user = userRepository.findUserById(id);
        model.addAttribute("user", user);
        logger.info("user obj from ID: {}", user);
        model.addAttribute("roles", roleRepository.findAll());
        logger.info("loading user for update w/ ID: {}", id);
        return "admin/admin-home";
    }

    @PostMapping(value = "/admin/update/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult, @PathVariable Long id) {
        logger.info("updating user w/ ID: {}", id);
        logger.info("user object received to update: {}", user);

        if (bindingResult.hasErrors()) {
            logger.error("Error updating user {}", user);
            return "admin/admin-home";
        }

        logger.info("User object after form submission: {}", user);

        userService.updateUser(id, user);
        return "redirect:/admin";
    }


    @PostMapping(value = "admin/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userRepository.findUserById(id));
        userService.removeUserById(id);
        logger.info("user has been deleted. User id {}", id);
        return "redirect:/admin";

    }


}
