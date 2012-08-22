package ru.testhf.srv3.h37945.dao.impl;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.testhf.srv3.h37945.dao.UserDAO;
import ru.testhf.srv3.h37945.domain.User;

import java.util.Iterator;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> userList() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public User getUserByLogin(String login) {
        List<User> users = sessionFactory.getCurrentSession().createQuery("from User where login = ?").setString(0, login).list();
        return users.get(0);
    }

    @Override
    public void deleteUser(String login) {
        User user = (User) sessionFactory.getCurrentSession().load(User.class, login);
        if (null != user) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }

    @Override
    public void updateUser(String login) {
        User user = (User) sessionFactory.getCurrentSession().load(User.class, login);
        user.setRole("ROLE_USER, ROLE_ADMIN");
        if (null != user) {
            sessionFactory.getCurrentSession().update(user);
        }
    }

    @Override
    public boolean isUserExist(String login) {
        return false;
    }
}
