package ru.testhf.srv3.h37945.service.dao.impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testhf.srv3.h37945.dao.GameDAO;
import ru.testhf.srv3.h37945.domain.Game;
import ru.testhf.srv3.h37945.service.dao.GameService;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDAO gameDAO;

    public int addGame(Game game) throws MySQLIntegrityConstraintViolationException {
        return gameDAO.addGame(game);
    }

    public List<Game> gamesList() {
        return gameDAO.gamesList();
    }

    public Game getGameById(int id) throws MySQLIntegrityConstraintViolationException {
        return gameDAO.getGameById(id);
    }

    public List<Game> gameListForUser(String login) {
        return gameDAO.gameListForUser(login);
    }

    public List<Game> completedGameListForUser(String login) {
        return gameDAO.completedGameListForUser(login);
    }

    public void changeMove(int id) {
        gameDAO.changeMove(id);
    }

    public void setWinner(int id, String login) {
        gameDAO.setWinner(id, login);
    }
}
