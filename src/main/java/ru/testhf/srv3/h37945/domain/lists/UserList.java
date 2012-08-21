package ru.testhf.srv3.h37945.domain.lists;

import ru.testhf.srv3.h37945.domain.User;

import java.util.List;

public class UserList {
    private List<User> users;

    public UserList(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
