package ru.testhf.srv3.h37945.domain;

import javax.persistence.*;
import java.io.Serializable;

public class Game implements Serializable {
    private int id;
    private String firstLogin;
    private String secondLogin;
    private boolean isCompleted;
    private String winner;
    private int idFirstField;
    private int idSecondField;
    private int move;

    public Game() {
    }

    public Game(String firstLogin, String secondLogin, boolean completed, String winner, int idFirstField, int idSecondField) {
        this.firstLogin = firstLogin;
        this.secondLogin = secondLogin;
        isCompleted = completed;
        this.winner = winner;
        this.idFirstField = idFirstField;
        this.idSecondField = idSecondField;
        move = 1;
    }

    public Game(int id, String firstLogin, String secondLogin, boolean completed, String winner, int idFirstField, int idSecondField, int move) {
        this.id = id;
        this.firstLogin = firstLogin;
        this.secondLogin = secondLogin;
        isCompleted = completed;
        this.winner = winner;
        this.idFirstField = idFirstField;
        this.idSecondField = idSecondField;
        this.move = move;
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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getIdFirstField() {
        return idFirstField;
    }

    public void setIdFirstField(int idFirstField) {
        this.idFirstField = idFirstField;
    }

    public int getIdSecondField() {
        return idSecondField;
    }

    public void setIdSecondField(int idSecondField) {
        this.idSecondField = idSecondField;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

}
