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
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;

    @GetMapping("/sign_up")
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "/auth/sign_up";
    }

    @PostMapping("/sign_up")
    public String signUp(
            @ModelAttribute @Valid User user,
            BindingResult result) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "/auth/sign_up";
        }
        userService.create(user);
        return "redirect:/users";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false) boolean error,
                        Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", true);
        }
        return "auth/sign_in";
    }
}
