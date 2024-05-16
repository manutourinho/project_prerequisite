package com.projectprerequisite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.projectprerequisite.model.User;
import com.projectprerequisite.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/")
    public String redirectToUsersPage() {
        return "redirect:/user";

    }

    @GetMapping("/user")
    public String homeUserPage() {
        return "home";

    }

    @GetMapping("user/list")
    public String listUsers(Model model) {
        try {
            List<User> users = userService.getAllUsers();
            model.addAttribute("user", users);
        } catch (Exception e) {
            model.addAttribute("user", null);
        }
        return "list";

    }

    @GetMapping("user/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "form";

    }

    @PostMapping("user/add")
    public String add(@ModelAttribute("user") User user) {
        userService.saveOrUpdateUser(user);
        return "redirect:/user/list";

    }

    @GetMapping(value = "user/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "update";

    }

    @PostMapping("user/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("user") User user) {
        userService.saveOrUpdateUser(user);
        return "redirect:/user/list";

    }

    @GetMapping(value = "user/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/user/list";

    }

}
