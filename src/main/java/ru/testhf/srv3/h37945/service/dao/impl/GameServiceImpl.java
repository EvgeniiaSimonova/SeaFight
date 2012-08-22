package ru.testhf.srv3.h37945.service.dao.impl;

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

    @Transactional
    public void addGame(Game game) {
        gameDAO.addGame(game);
    }

    @Transactional
    public List<Game> gamesList() {
        return gameDAO.gamesList();
    }

    @Transactional
    public Game getGameById(int id) {
        return gameDAO.getGameById(id);
    }

    @Transactional
    public List<Game> gameListForUser(String login) {
        return gameDAO.gameListForUser(login);
    }

    @Transactional
    public List<Game> completedGameListForUser(String login) {
        return gameDAO.completedGameListForUser(login);
    }

    @Transactional
    public void changeMove(int id) {
        gameDAO.changeMove(id);
    }

    @Transactional
    public void setWinner(int id, String login) {
        gameDAO.setWinner(id, login);
    }
}
