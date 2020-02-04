package com.slava.dao;

import com.slava.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    User getByEmail(String email);

    void create(User user);
}
