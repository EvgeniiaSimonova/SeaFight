package ru.testhf.srv3.h37945.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.testhf.srv3.h37945.dao.GameDAO;
import ru.testhf.srv3.h37945.domain.Field;
import ru.testhf.srv3.h37945.domain.Game;
import ru.testhf.srv3.h37945.domain.Request;

import java.util.List;

@Repository
public class GameDAOImpl implements GameDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addGame(Game game) {
        sessionFactory.getCurrentSession().save(game);
    }

    @Override
    public List<Game> gamesList() {
        return sessionFactory.getCurrentSession().createQuery("from Game").list();
    }

    @Override
    public Game getGameById(int id) {
        List<Game> games = sessionFactory.getCurrentSession().
                createQuery("from Game where id = ?").setInteger(0, id).list();
        return games.get(0);
    }

    @Override
    public List<Game> gameListForUser(String login) {
        return sessionFactory.getCurrentSession().
                createQuery("from Game where (secondLogin = ? OR firstLogin = ?) AND isCompleted = ?").
                setString(0, login).setString(1, login).setBoolean(2, false).list();
    }

    @Override
    public List<Game> completedGameListForUser(String login) {
        return sessionFactory.getCurrentSession().
                createQuery("from Game where (secondLogin = ? OR firstLogin = ?) AND isCompleted = ?").
                setString(0, login).setString(1, login).setBoolean(2, true).list();
    }

    @Override
    public void changeMove(int id) {
        Game game = getGameById(id);
        if (game.getMove() == 1) {
            game.setMove(2);
        } else {
            game.setMove(1);
        }
        sessionFactory.getCurrentSession().update(game);
    }

    @Override
    public void setWinner(int id, String login) {
        Game game = getGameById(id);
        game.setCompleted(true);
        game.setWinner(login);
        sessionFactory.getCurrentSession().update(game);
    }
}
