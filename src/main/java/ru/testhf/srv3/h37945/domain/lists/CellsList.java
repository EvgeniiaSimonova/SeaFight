package ru.testhf.srv3.h37945.domain.lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CellsList {

    private char[] c;
    List<String> cells;

    public void setFreeCells() {
        c = new char[100];
        for (int i = 0; i < 100; i++) {
            c[i] = '.';
        }
    }

    public void setDecks(String s) {
        String[] decks = s.split(",");
        for (int i = 0; i < decks.length; i++) {
            c[Integer.parseInt(decks[i])] = 'd';
        }
    }

    public void setShots(String s) {
        if (!s.equals("")) {
            String[] shots = s.split(",");
            for (int i = 0; i < shots.length; i++) {
                if (c[Integer.parseInt(shots[i])] == 'd') {
                    c[Integer.parseInt(shots[i])] = 'k';
                } else  {
                    c[Integer.parseInt(shots[i])] = 'o';
                }
            }
        }
    }

    public void hideCells() {
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 'd') {
                c[i] = '.';
            }
        }
    }

    public void setMyCells(String decks, String shots) {
        setFreeCells();
        setDecks(decks);
        setShots(shots);
        cells = new ArrayList<String>();
        for (int i = 0; i < c.length; i++) {
            cells.add(c[i] + "");
        }
    }

    public void setOpponentCells(String decks, String shots) {
        setFreeCells();
        setDecks(decks);
        setShots(shots);
        hideCells();
        cells = new ArrayList<String>();
        for (int i = 0; i < c.length; i++) {
            cells.add(c[i] + "");
        }
    }

    public void print() {
        Iterator<String> iterator = cells.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public List<String> getCells() {
        return cells;
    }
}
