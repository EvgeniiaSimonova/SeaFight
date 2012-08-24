package ru.testhf.srv3.h37945.dao.impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import ru.testhf.srv3.h37945.dao.FieldDAO;
import ru.testhf.srv3.h37945.domain.Field;
import ru.testhf.srv3.h37945.domain.Game;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class FieldDAOImpl implements FieldDAO {

    private static final Logger logger = LoggerFactory.getLogger(FieldDAOImpl.class);

    @Autowired
    private DriverManagerDataSource driverManagerDataSource;

    @Override
    public int addField(Field field) throws MySQLIntegrityConstraintViolationException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int id = -1;
        try {
            connection = driverManagerDataSource.getConnection();
            statement = connection.createStatement();
            String request = "insert into Fields (ships, shots, isKilled) " +
                    "values ('"+field.getShips()+"', '"+field.getShots()+"', "+field.isKilled()+")";
            statement.executeUpdate(request, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new MySQLIntegrityConstraintViolationException("Can't create new field");
        } finally {
            closeAll(connection, statement, resultSet);
            return id;
        }
    }

    @Override
    public Field getFieldById(int id) throws MySQLIntegrityConstraintViolationException {
        Field field = new Field();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = driverManagerDataSource.getConnection();
            statement = connection.createStatement();
            String request = "select * from Fields where id = " + id;
            resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                field = new Field(resultSet.getInt("id"),
                        resultSet.getString("ships"),
                        resultSet.getString("shots"),
                        resultSet.getBoolean("isKilled"));
            }
        } catch (SQLException e) {
        } finally {
            closeAll(connection, statement, resultSet);
            if (field != null) {
                return field;
            } else {
                throw new MySQLIntegrityConstraintViolationException("Can't find field with id = " + id);
            }

        }
    }

    @Override
    public List<Field> fieldList() {
        List<Field> fields = new ArrayList<Field>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = driverManagerDataSource.getConnection();
            statement = connection.createStatement();
            String request = "select * from Fields";
            resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                Field field = new Field(resultSet.getInt("id"),
                        resultSet.getString("ships"),
                        resultSet.getString("shots"),
                        resultSet.getBoolean("isKilled"));
                fields.add(field);
            }
        } catch (SQLException e) {
        } finally {
            closeAll(connection, statement, resultSet);
            return fields;
        }
    }

    @Override
    public void setShips(int id, String ships) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = driverManagerDataSource.getConnection();
            statement = connection.createStatement();
            String request = "update Fields set ships = '"+ships+"' where id = " + id;
            statement.executeUpdate(request);
        } catch (SQLException e) {

        }finally {
            closeResourceConnection(connection);
            closeResourceStatement(statement);
        }
    }

    @Override
    public void addShot(int id, int cell) {
        Connection connection = null;
        Statement statement = null;
        try {
            Field field = getFieldById(id);
            String shots = field.getShots() + cell + ",";
            connection = driverManagerDataSource.getConnection();
            statement = connection.createStatement();
            String request = "update Fields set shots = '"+shots+"' where id = " + id;
            statement.executeUpdate(request);
        } catch (SQLException e) {
        }finally {
            closeResourceConnection(connection);
            closeResourceStatement(statement);
            try {
                if (isKilled(id)) {
                    try {
                        connection = driverManagerDataSource.getConnection();
                        statement = connection.createStatement();
                        String request = "update Fields set isKilled = true where id = " + id;
                        statement.executeUpdate(request);
                    } catch (SQLException e) {

                    }finally {
                        closeResourceConnection(connection);
                        closeResourceStatement(statement);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public boolean isKilled(int id) {
        List<String> list = new ArrayList<String>();

        try {
            Field field = getFieldById(id);
            String[] ships = field.getShips().split(",");
            String[] shots = field.getShots().split(",");
            for (int i = 0; i < ships.length; i++) {
                if (!ships[i].equals("")) {
                    list.add(ships[i]);
                }
            }

            for (int i = 0; i < shots.length; i++) {
                list.remove(shots[i]);
                logger.info("list "+ list.toString());
            }
        } catch (Exception e) {}
        if (list.size() == 0) {
            return true;
        } else {
            return false;
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
