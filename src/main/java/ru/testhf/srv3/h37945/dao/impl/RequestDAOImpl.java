package ru.testhf.srv3.h37945.dao.impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import ru.testhf.srv3.h37945.dao.RequestDAO;
import ru.testhf.srv3.h37945.domain.Request;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RequestDAOImpl implements RequestDAO {

    @Autowired
    private DriverManagerDataSource driverManagerDataSource;

    @Override
    public void addRequest(Request request) throws MySQLIntegrityConstraintViolationException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = driverManagerDataSource.getConnection();
            statement = connection.createStatement();
            String requestSQL = "insert into Requests (firstLogin, secondLogin, state, idGame) " +
                    "values ('"+request.getFirstLogin()+"', '"+request.getSecondLogin()+"', "+request.getState()+", "+request.getIdGame()+")";
            statement.executeUpdate(requestSQL);
        } catch (SQLException e) {
            throw new MySQLIntegrityConstraintViolationException("Can't create this request");
        } finally {
            closeResourceConnection(connection);
            closeResourceStatement(statement);
        }
    }

    @Override
    public List<Request> requestList() {
        List<Request> requests = new ArrayList<Request>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = driverManagerDataSource.getConnection();
            statement = connection.createStatement();
            String requestSQL = "select * from Requests";
            resultSet = statement.executeQuery(requestSQL);
            while (resultSet.next()) {
                Request request = new Request(resultSet.getInt("id"),
                        resultSet.getString("firstLogin"),
                        resultSet.getString("secondLogin"),
                        resultSet.getInt("state"),
                        resultSet.getInt("idGame"));
                requests.add(request);
            }
        } catch (SQLException e) {
        } finally {
            closeAll(connection, statement, resultSet);
            return requests;
        }
    }

    @Override
    public Request getRequestById(int id) throws MySQLIntegrityConstraintViolationException {
        Request request = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = driverManagerDataSource.getConnection();
            statement = connection.createStatement();
            String requestSQL = "select * from Requests where id = " + id;
            resultSet = statement.executeQuery(requestSQL);
            while (resultSet.next()) {
                request = new Request(resultSet.getInt("id"),
                        resultSet.getString("firstLogin"),
                        resultSet.getString("secondLogin"),
                        resultSet.getInt("state"),
                        resultSet.getInt("idGame"));
            }
        } catch (SQLException e) {
        } finally {
            closeAll(connection, statement, resultSet);
            if (request!=null) {
                return request;
            } else {
                throw new MySQLIntegrityConstraintViolationException("Can't find request with id = " + id);
            }
        }
    }

    @Override
    public void updateRequest(int id, int state, int idGame) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = driverManagerDataSource.getConnection();
            statement = connection.createStatement();
            String request = "update Requests set state = "+state+" where id = " + id;
            statement.executeUpdate(request);
            request = "update Requests set idGame = "+idGame+" where id = " + id;
            statement.executeUpdate(request);
        } catch (SQLException e) {

        }finally {
            closeResourceConnection(connection);
            closeResourceStatement(statement);
        }
    }

    @Override
    public List<Request> requestsForUser(String login) {
        List<Request> requests = new ArrayList<Request>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = driverManagerDataSource.getConnection();
            statement = connection.createStatement();
            String requestSQL = "select * from Requests where secondLogin = '" +login+"' AND state = 0";
            resultSet = statement.executeQuery(requestSQL);
            while (resultSet.next()) {
                Request request = new Request(resultSet.getInt("id"),
                        resultSet.getString("firstLogin"),
                        resultSet.getString("secondLogin"),
                        resultSet.getInt("state"),
                        resultSet.getInt("idGame"));
                requests.add(request);
            }
        } catch (SQLException e) {
        } finally {
            closeAll(connection, statement, resultSet);
            return requests;
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