package ru.testhf.srv3.h37945.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Fields")
public class Field implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String ships;

    @Column
    private String shots;

    @Column
    private boolean isKilled;

    public Field() {
    }

    public Field(String ships, String shots, boolean killed) {
        this.ships = ships;
        this.shots = shots;
        isKilled = killed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShips() {
        return ships;
    }

    public void setShips(String ships) {
        this.ships = ships;
    }

    public String getShots() {
        return shots;
    }

    public void setShots(String shots) {
        this.shots = shots;
    }

    public boolean isKilled() {
        return isKilled;
    }

    public void setKilled(boolean killed) {
        isKilled = killed;
    }
}
