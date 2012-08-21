package ru.testhf.srv3.h37945.logic.entity;

public class Cell {

    private char letter;
    private char figure;

    public Cell() {
    }

    public Cell(char letter, char figure) {
        this.letter = letter;
        this.figure = figure;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public char getFigure() {
        return figure;
    }

    public void setFigure(char figure) {
        this.figure = figure;
    }
}
