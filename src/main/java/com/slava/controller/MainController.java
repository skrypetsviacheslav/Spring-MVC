package com.slava.controller;

import com.slava.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class MainController {

    private static Collection<User> users = new ArrayList<>();

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
        model.addAttribute("users", users);
        return "/users";
    }

    @GetMapping("/users/new")
    public String getSignUp() {
        return "/sign_up";
    }

    @PostMapping("/users/new")
    public String signUp(
//            @RequestParam("name") String name,
//            @RequestParam("surname") String surname,
//            @RequestParam("email") String email
            @ModelAttribute User user
    ) {
//        users.add(new User(name, surname, email));
        users.add(user);

        return "redirect:/users";
    }

}
