package ru.testhf.srv3.h37945.dao;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.testhf.srv3.h37945.domain.User;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveUser(User user) {
        /*Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();*/
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> userList() {
        /*Session session = sessionFactory.getCurrentSession();
        List<User> users = session.createCriteria(User.class).list();
        session.getTransaction().commit();
        return users;*/
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public User getUserByLogin(String login) {
        /*Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, login);
        session.getTransaction().commit();
        return user;*/
        List<User> users = sessionFactory.getCurrentSession().createQuery("from User where login = ?").setString(0, login).list();
        return users.get(0);
    }

    @Override
    public void deleteUser(String login) {
        /*Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(getUserByLogin(login));
        session.getTransaction().commit();*/
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
}
