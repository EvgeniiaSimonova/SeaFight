package ru.testhf.srv3.h37945.forms;

import java.util.ArrayList;
import java.util.List;

public class TableForm {
    private int[] number;

    public TableForm(){}

    public TableForm(int[] number) {
        this.number = number;
    }

    public int[] getNumber() {
        return number;
    }

    public void setNumber(int[] number) {
        this.number = number;
    }

    public void print() {
        for (int i = 0; i < number.length; i++) {
            System.out.println(number[i]);
        }
    }

    public String toString() {
        String s = number[0] + "";
        for (int i = 1; i < number.length; i++) {
            s = s + "," +number[i];
        }
        return s;
    }
}
