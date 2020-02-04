package com.slava.controller;

import com.slava.dao.UserDao;
import com.slava.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {

    private final UserDao userDao;

    @Autowired
    public MainController(UserDao userDao) {
        this.userDao = userDao;
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
        model.addAttribute("users", userDao.getAll());
        return "/users";
    }

    @GetMapping("/users/new")
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "/sign_up";
    }

//    @PostMapping("/users/new")
//    public String signUp(
//            @ModelAttribute @Valid User user,
//            BindingResult result) {
//        if (result.hasErrors()) {
//            return "/sign_up";
//        }
//
//        users.add(user);
//        return "redirect:/users";
//    }

}
