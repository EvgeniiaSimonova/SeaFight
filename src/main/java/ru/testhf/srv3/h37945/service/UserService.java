package ru.testhf.srv3.h37945.service;

import ru.testhf.srv3.h37945.domain.User;

import java.util.List;

public interface UserService {
    public void saveUser(User user);

    public List<User> userList();

    public User getUserByLogin(String login);
}
