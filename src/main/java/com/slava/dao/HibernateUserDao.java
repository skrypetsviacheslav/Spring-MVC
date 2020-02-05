package com.slava.dao;

import com.slava.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HibernateUserDao implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateUserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<User> getAll() {
        return currentSession().createQuery("from  User", User.class).list();
    }

    @Override
    public User getByEmail(String email) {
        return currentSession().createQuery(
                "from User where email=:email", User.class)
                .setParameter("email", email)
                .list().stream().findAny().orElse(null);
    }

    @Override
    public void create(User user) {
        currentSession().save(user);
    }
}
