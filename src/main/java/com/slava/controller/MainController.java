package com.slava.controller;

import com.slava.model.User;
import com.slava.service.UserService;
import com.slava.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {
    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public MainController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/{name}")
    public String index(@PathVariable String name,
                        Model model) {
        model.addAttribute("msg", "Hello, " + name);
        return "/index";
    }

    @GetMapping("/raw")
    @ResponseBody
    public String raw() {
        return "Raw data";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "/users";
    }

    @GetMapping("/users/new")
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "/sign_up";
    }

    @PostMapping("/users/new")
    public String signUp(
            @ModelAttribute @Valid User user,
            BindingResult result) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "/sign_up";
        }
        userService.create(user);
        return "redirect:/users";
    }

}
