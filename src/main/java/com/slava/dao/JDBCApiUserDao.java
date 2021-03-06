package com.slava.dao;

import com.slava.model.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class JDBCApiUserDao implements UserDao {
    private static Connection conn;

    static {
        String url = null;
        String username = null;
        String password = null;

        try (InputStream in = UserDao
                .class.getClassLoader().getResourceAsStream("persistence.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            url = properties.getProperty("jdbc.url");
            username = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement("select  * from  users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setName(rs.getString(1));
                u.setSurname(rs.getString(2));
                u.setEmail(rs.getString(3));
                users.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    @Override
    public User getByEmail(String email) {
        try {
            PreparedStatement ps = conn.prepareStatement("select  * from  users where email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setName(rs.getString(1));
                u.setSurname(rs.getString(2));
                u.setEmail(rs.getString(3));
                return u;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void create(User user) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into users values (?,?,?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
