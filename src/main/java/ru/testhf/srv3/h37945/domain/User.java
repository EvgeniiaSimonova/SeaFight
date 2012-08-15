package ru.testhf.srv3.h37945.domain;


import javax.persistence.*;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = User.NamedQuery.USER_FIND_ALL, query = "from User"),
        @NamedQuery(name = User.NamedQuery.USER_FIND_BY_ID, query = "from User where id = :id") })
@NamedNativeQueries({
        @NamedNativeQuery(name = User.NamedQuery.USER_FIND_BY_NAME, query = "select * from User where name like :name", resultClass = User.class) })

@Entity
@Table(name="Users")
public class User implements Serializable{
    @Id
    @Column
    private String login;
    @Column
    private String password;
    @Column
    private String role;

    public User() {
    }

    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static class NamedQuery {
        public static final String USER_FIND_ALL = "User.findAll";
        public static final String USER_FIND_BY_ID = "User.findById";
        public static final String USER_FIND_BY_NAME = "User.findByName";
    }

    public String toString() {
        return new String(login + " " + password + " " + role);
    }
}
