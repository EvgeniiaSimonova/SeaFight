package ru.testhf.srv3.h37945.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testhf.srv3.h37945.dao.UserDAO;
import ru.testhf.srv3.h37945.domain.User;
import ru.testhf.srv3.h37945.service.dao.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Transactional
    public List<User> userList() {
        return userDAO.userList();
    }

    @Transactional
    public User getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }

    @Transactional
    public void deleteUser(String login) {
        userDAO.deleteUser(login);
    }

    @Transactional
    public void updateUser(String login) {
        userDAO.updateUser(login);
    }

    public boolean isUserExist(String login) {
        return userDAO.isUserExist(login);
    }
}
