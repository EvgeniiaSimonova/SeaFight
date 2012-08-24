package ru.testhf.srv3.h37945.domain;

import javax.persistence.*;
import java.io.Serializable;

public class Request implements Serializable {
    private int id;
    private String firstLogin;
    private String secondLogin;
    private int state;
    private int idGame;

    public Request() {
    }

    public Request(String firstLogin, String secondLogin, int state, int idGame) {
        this.firstLogin = firstLogin;
        this.secondLogin = secondLogin;
        this.state = state;
        this.idGame = idGame;
    }

    public Request(int id, String firstLogin, String secondLogin, int state, int idGame) {
        this.id = id;
        this.firstLogin = firstLogin;
        this.secondLogin = secondLogin;
        this.state = state;
        this.idGame = idGame;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(String firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getSecondLogin() {
        return secondLogin;
    }

    public void setSecondLogin(String secondLogin) {
        this.secondLogin = secondLogin;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public String toString() {
        return new String("id = " + id + " firstLogin = " + firstLogin + " secondLogin = " + secondLogin + " state = " + state);
    }
}
