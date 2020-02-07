package com.slava.controller;

import com.slava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "redirect:/users";
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
}
