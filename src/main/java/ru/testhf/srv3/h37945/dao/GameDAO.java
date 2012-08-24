package ru.testhf.srv3.h37945.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import ru.testhf.srv3.h37945.domain.Game;

import java.util.List;

public interface GameDAO {
    public int addGame(Game game) throws MySQLIntegrityConstraintViolationException;

    public List<Game> gamesList();

    public Game getGameById(int id) throws MySQLIntegrityConstraintViolationException;

    public List<Game> gameListForUser(String login);

    public List<Game> completedGameListForUser(String login);

    public void changeMove(int id);

    public void setWinner(int id, String login);
}
