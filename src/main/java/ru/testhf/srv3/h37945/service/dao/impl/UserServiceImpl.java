package ru.testhf.srv3.h37945.service.dao.impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
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

    public void saveUser(User user) throws MySQLIntegrityConstraintViolationException {
        userDAO.saveUser(user);
    }

    public List<User> userList() {
        return userDAO.userList();
    }

    public User getUserByLogin(String login) throws MySQLIntegrityConstraintViolationException{
        return userDAO.getUserByLogin(login);
    }

    public void deleteUser(String login) throws MySQLIntegrityConstraintViolationException {
        userDAO.deleteUser(login);
    }

    public void updateUser(String login) throws MySQLIntegrityConstraintViolationException {
        userDAO.updateUser(login);
    }

    public boolean isUserExist(String login) {
        return userDAO.isUserExist(login);
    }
}
