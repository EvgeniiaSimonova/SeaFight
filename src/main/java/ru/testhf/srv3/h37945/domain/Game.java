package ru.testhf.srv3.h37945.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Games")
public class Game implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String firstLogin;

    @Column
    private String secondLogin;

    @Column
    private boolean isCompleted;

    @Column
    private String winner;

    @Column
    private int idFirstField;

    @Column
    private int idSecondField;

    @Column
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
