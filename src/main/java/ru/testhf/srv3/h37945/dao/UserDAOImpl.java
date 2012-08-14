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
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
    }

    @Override
    public List<User> userList() {
        Session session = sessionFactory.openSession();
        List<User> users = session.createCriteria(User.class).list();
        return users;
    }

    @Override
    public User getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        return (User) session.load(User.class, login);
    }
}
