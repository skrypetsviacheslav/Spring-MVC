package com.slava.dao;

import com.slava.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class JDBCTemplateUserDao implements UserDao {
    public final BeanPropertyRowMapper<User> ROW_MAPPER =
            new BeanPropertyRowMapper<>(User.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCTemplateUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(
                "select * from  users",
                ROW_MAPPER);
//        return jdbcTemplate.query("select * from users", (rs, rowNum) -> {
//            User u = new User();
//            u.setName(rs.getString(1));
//            u.setSurname(rs.getString(2));
//            u.setEmail(rs.getString(3));
//            return u;
//        });
    }

    @Override
    public User getByEmail(String email) {
        return jdbcTemplate.query(
                "select * from users where email = ?",
                new Object[]{email},
                ROW_MAPPER)
                .stream().findAny().orElse(null);
    }

    @Override
    public void create(User user) {
        jdbcTemplate.update(
                "insert into users values (?,?,?)",
                user.getName(), user.getSurname(), user.getEmail());
    }
}
