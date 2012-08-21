package ru.testhf.srv3.h37945.forms;

public class ShotForm {
    private String letter;
    private String figure;

    public ShotForm() {
    }

    public ShotForm(String letter, String figure) {
        this.letter = letter;
        this.figure = figure;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }
}
