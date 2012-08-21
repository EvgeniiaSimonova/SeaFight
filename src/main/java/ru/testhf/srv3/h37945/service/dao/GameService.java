package ru.testhf.srv3.h37945.service.dao;

import ru.testhf.srv3.h37945.domain.Game;

import java.util.List;

public interface GameService {
    public void addGame(Game game);

    public List<Game> gamesList();

    public Game getGameById(int id);

    public List<Game> gameListForUser(String login);

    public List<Game> completedGameListForUser(String login);

    public void changeMove(int id);

    public void setWinner(int id, String login);
}
