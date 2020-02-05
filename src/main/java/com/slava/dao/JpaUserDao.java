package com.slava.dao;

import com.slava.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Primary
@Transactional(readOnly = true)
public class JpaUserDao implements UserDao {

    @PersistenceContext(unitName = "emf")
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        return entityManager.createQuery(
                "select u from User u", User.class)
                .getResultList();
    }

    @Override
    public User getByEmail(String email) {
        return entityManager.createQuery(
                "select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList().stream().findAny().orElse(null);
    }

    @Override
    @Transactional
    public void create(User user) {
        entityManager.persist(user);
    }
}
