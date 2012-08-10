package ru.testhf.srv3.h37945.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.testhf.srv3.h37945.domain.User;
import ru.testhf.srv3.h37945.domain.UserDB;

@Service
public class UserManager {

    public UserManager() {
    }

    public User getUser(String username) throws UsernameNotFoundException {
        SessionFactory sessionFactory = new Configuration().buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        UserDB userDB = (UserDB) session.get(UserDB.class, username);
        transaction.commit();
        return new User(userDB.getLogin(), userDB.getPassword(), userDB.getRole());

    }
}
