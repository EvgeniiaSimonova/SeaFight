package ru.testhf.srv3.h37945.dao;

import ru.testhf.srv3.h37945.domain.User;

import java.util.List;

public interface UserDAO {
    public void saveUser(User user);

    public List<User> userList();

    public User getUserByLogin(String login);

    public void deleteUser(String login);

    public void updateUser(String login);

    public boolean isUserExist(String login);
}
