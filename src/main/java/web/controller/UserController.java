package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("user/form");
        User user = new User();
        modelAndView.addObject("user", user);

        return modelAndView;

    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("user/form");
        User user = userService.getUserById(id);
        modelAndView.addObject("user", user);

        return modelAndView;

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("userForm") User user) {
        userService.saveOrUpdateUser(user);

        return new ModelAndView("redirect:/user/list");

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") long id) {
        userService.removeUserById(id);

        return new ModelAndView("redirect:/user/list");

    }

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("user/list");
        List<User> users = userService.getAllUsers();
        modelAndView.addObject("users", users);

        return modelAndView;

    }


}
