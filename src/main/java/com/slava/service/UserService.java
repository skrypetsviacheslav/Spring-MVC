package com.slava.service;

import com.slava.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getByEmail(String email);

    void create(User user);
}
