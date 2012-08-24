package ru.testhf.srv3.h37945.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import ru.testhf.srv3.h37945.domain.User;

import java.util.List;

public interface UserDAO {
    public void saveUser(User user) throws MySQLIntegrityConstraintViolationException;

    public List<User> userList();

    public User getUserByLogin(String login) throws MySQLIntegrityConstraintViolationException;

    public void deleteUser(String login) throws MySQLIntegrityConstraintViolationException;

    public void updateUser(String login) throws MySQLIntegrityConstraintViolationException;

    public boolean isUserExist(String login);
}
