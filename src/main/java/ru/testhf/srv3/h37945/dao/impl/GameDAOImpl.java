package ru.testhf.srv3.h37945.dao.impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import ru.testhf.srv3.h37945.dao.GameDAO;
import ru.testhf.srv3.h37945.domain.Game;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GameDAOImpl implements GameDAO{

    @Autowired
    private DriverManagerDataSource driverManagerDataSource;

    @Override
    public int addGame(Game game) throws MySQLIntegrityConstraintViolationException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int id = -1;
        try {
            connection = driverManagerDataSource.getConnection();
            statement = connection.createStatement();
            String request = "insert into Games (firstLogin, secondLogin, isCompleted, winner, idFirstField, idSecondField, move) " +
                    "values ('"+game.getFirstLogin()+"', '"+game.getSecondLogin()+"', false, '', "+game.getIdFirstField()+", "+game.getIdSecondField()+", 1)";
            statement.executeUpdate(request, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new MySQLIntegrityConstraintViolationException("Can't create this game");
        } finally {
            closeAll(connection, statement, resultSet);
            return id;
        }
    }

    @Override
    public List<Game> gamesList() {
        List<Game> games = new ArrayList<Game>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = driverManagerDataSource.getConnection();
            statement = connection.createStatement();
            String request = "select * from Games";
            resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                Game game = new Game(resultSet.getInt("id"),
                        resultSet.getString("firstLogin"),
                        resultSet.getString("secondLogin"),
                        resultSet.getBoolean("isCompleted"),
                        resultSet.getString("winner"),
                        resultSet.getInt("idFirstField"),
                        resultSet.getInt("idSecondField"),
                        resultSet.getInt("move"));
                games.add(game);
            }
        } catch (SQLException e) {
        } finally {
            closeAll(connection, statement, resultSet);
            return games;
        }
    }

    @Override
    public Game getGameById(int id) throws MySQLIntegrityConstraintViolationException {
        Game game = new Game();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = driverManagerDataSource.getConnection();
            statement = connection.createStatement();
            String request = "select * from Games where id = " + id;
            resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                game = new Game(resultSet.getInt("id"),
                        resultSet.getString("firstLogin"),
                        resultSet.getString("secondLogin"),
                        resultSet.getBoolean("isCompleted"),
                        resultSet.getString("winner"),
                        resultSet.getInt("idFirstField"),
                        resultSet.getInt("idSecondField"),
                        resultSet.getInt("move"));
            }
        } catch (SQLException e) {
        } finally {
            closeAll(connection, statement, resultSet);
            if (game!=null) {
                return game;
            } else {
                throw new MySQLIntegrityConstraintViolationException("Can't find Game with id = " + id);
            }
        }
    }

    @Override
    public List<Game> gameListForUser(String login) {
        List<Game> games = new ArrayList<Game>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = driverManagerDataSource.getConnection();
            statement = connection.createStatement();
            String request = "select * from Games where (firstLogin = '"+login+"' OR secondLogin = '"+login+"') AND isCompleted = false";
            resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                Game game = new Game(resultSet.getInt("id"),
                        resultSet.getString("firstLogin"),
                        resultSet.getString("secondLogin"),
                        resultSet.getBoolean("isCompleted"),
                        resultSet.getString("winner"),
                        resultSet.getInt("idFirstField"),
                        resultSet.getInt("idSecondField"),
                        resultSet.getInt("move"));
                games.add(game);
            }
        } catch (SQLException e) {
        } finally {
            closeAll(connection, statement, resultSet);
            return games;
        }
    }

    @Override
    public List<Game> completedGameListForUser(String login) {
        List<Game> games = new ArrayList<Game>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = driverManagerDataSource.getConnection();
            statement = connection.createStatement();
            String request = "select * from Games where (firstLogin = '"+login+"' OR secondLogin = '"+login+"') AND isCompleted = true";
            resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                Game game = new Game(resultSet.getInt("id"),
                        resultSet.getString("firstLogin"),
                        resultSet.getString("secondLogin"),
                        resultSet.getBoolean("isCompleted"),
                        resultSet.getString("winner"),
                        resultSet.getInt("idFirstField"),
                        resultSet.getInt("idSecondField"),
                        resultSet.getInt("move"));
                games.add(game);
            }
        } catch (SQLException e) {
        } finally {
            closeAll(connection, statement, resultSet);
            return games;
        }
    }

    @Override
    public void changeMove(int id) {
        Connection connection = null;
        Statement statement = null;
        try {
            Game game = getGameById(id);
            int move;
            if (game.getMove() == 1) {
                move = 2;
            } else {
                move = 1;
            }
            connection = driverManagerDataSource.getConnection();
            statement = connection.createStatement();
            String request = "update Games set move = "+move+" where id = " + id;
            statement.executeUpdate(request);
        } catch (SQLException e) {

        }finally {
            closeResourceConnection(connection);
            closeResourceStatement(statement);
        }
    }

    @Override
    public void setWinner(int id, String login) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = driverManagerDataSource.getConnection();
            statement = connection.createStatement();
            String request = "update Games set isCompleted = true where id = " + id;
            statement.executeUpdate(request);
            request = "update Games set winner = '"+login+"' where id = " + id;
            statement.executeUpdate(request);
        } catch (SQLException e) {

        }finally {
            closeResourceConnection(connection);
            closeResourceStatement(statement);
        }
    }

    private void closeAll(Connection connection, Statement statement, ResultSet resultSet) {
        closeResourceResultSet(resultSet);
        closeResourceStatement(statement);
        closeResourceConnection(connection);
    }

    private void closeResourceResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    private void closeResourceStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    private void closeResourceConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
    }
}
