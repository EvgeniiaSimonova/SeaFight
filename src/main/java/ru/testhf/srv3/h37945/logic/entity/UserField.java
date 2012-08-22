package ru.testhf.srv3.h37945.logic.entity;

import java.util.List;

public class UserField {

    private List<Cell> busyCells;
    private List<Cell> shots;
    private boolean killed;

    public UserField() {
    }

    public UserField(List<Cell> busyCells, List<Cell> shots, boolean killed) {
        this.busyCells = busyCells;
        this.shots = shots;
        this.killed = killed;
    }

    public List<Cell> getBusyCells() {
        return busyCells;
    }

    public void setBusyCells(List<Cell> busyCells) {
        this.busyCells = busyCells;
    }

    public List<Cell> getShots() {
        return shots;
    }

    public void setShots(List<Cell> shots) {
        this.shots = shots;
    }

    public boolean isKilled() {
        return killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }
}
