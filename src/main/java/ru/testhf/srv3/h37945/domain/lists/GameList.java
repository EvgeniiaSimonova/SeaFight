package ru.testhf.srv3.h37945.domain.lists;

import ru.testhf.srv3.h37945.domain.Game;

import java.util.Iterator;
import java.util.List;

public class GameList {

    private List<Game> games;

    public GameList(List<Game> games) {
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }

    public boolean isIdIncludeList(int id) {
        Iterator<Game> iterator = games.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                return true;
            }
        }
        return false;
    }
}
