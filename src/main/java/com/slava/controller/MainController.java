package com.slava.controller;

import com.slava.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Collection;

@Controller
public class MainController {
    @GetMapping("/{name}")
    public String index(
            @PathVariable String name,
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
        Collection<User> users = Arrays.asList(
                new User("John", "Smith", "js@mail.com"),
                new User("Mike", "Johnson", "mj@mail.com")
        );
        model.addAttribute("users", users);
        return "/users";
    }
}
