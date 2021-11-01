package com.solution.lushkov.dao.impl;

import com.solution.lushkov.dao.UserDao;
import com.solution.lushkov.entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class with realized CRUD methods for table User.
 * Extends GenericJdbcDao and implements UserDao.
 * @see User
 * @see AbstractDaoImpl
 * @see UserDao
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {
    /**
     * Field to use logging functions.
     */
    private static final Logger LOG = LogManager
            .getLogger(UserDaoImpl.class.getName());

    public static final String insertSql = "INSERT INTO user_table " +
            "(login, user_password, email, firstName, lastName, " +
            "birth_date, role_ID) VALUES (?, ?, ?, ?, ?, ?, ?);";
    public static final String findAllSql = "SELECT * FROM user_table;";
    public static final String findByIdSql = "SELECT * FROM user_table WHERE user_ID = ?;";
    public static final String findByLoginSql = "SELECT * FROM user_table WHERE login = ?;";
    public static final String findByEmailSql = "SELECT * FROM user_table WHERE email = ?;";
    public static final String updateSql = "UPDATE user_table SET login = ?, " +
            "user_password = ?, email = ?, firstName = ?, lastName = ?," +
            " birth_date = ?, role_ID = ? WHERE user_ID = ?";
    public static final String deleteSql = "DELETE FROM user_table WHERE user_ID = ?;";


    //Create
    /**
     * Creates tuple in table User_Table in DB
     * by data from parameter {@code entity}.
     *
     * @param entity the entity that need to create in DB.
     */
    @Override
    public void create(User entity) {
        connection = dataBaseConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(insertSql);
            setValuesInPreparedStatementWithoutId(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            finallyClosing(preparedStatement, connection);
        }
    }

    //Read
    /**
     * Read and save in list all entities from User_Table.
     *
     * @return list of all entities from User_Table.
     */
    @Override
    public List<User> findAll() {
        connection = dataBaseConnection.getConnection();
        List<User> usersList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(findAllSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                setUserValues(user, resultSet);
                usersList.add(user);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            finallyClosing(preparedStatement, connection);
        }
        return usersList;
    }

    /**
     * Find and return entity with such ID in User_Table.
     *
     * @param id the ID of entity.
     * @return entity with ID like in parameter.
     */
    @Override
    public User findById(Long id) {
        connection = dataBaseConnection.getConnection();
        PreparedStatement preparedStatement = null;
        User user = new User();
        try {
            preparedStatement = connection.prepareStatement(findByIdSql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                setUserValues(user, resultSet);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            finallyClosing(preparedStatement, connection);
        }
        return user;
    }

    /**
     * Find and return entity with such login in User_Table.
     *
     * @param login login of user.
     * @return entity with login like in parameter.
     */
    @Override
    public User findByLogin(String login) {
        connection = dataBaseConnection.getConnection();
        PreparedStatement preparedStatement = null;
        User user = new User();
        try {
            preparedStatement = connection.prepareStatement(findByLoginSql);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                setUserValues(user, resultSet);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            finallyClosing(preparedStatement, connection);
        }
        return user;
    }

    /**
     * Find and return entity with such email in User_Table.
     *
     * @param email email of user.
     * @return entity with email like in parameter.
     */
    @Override
    public User findByEmail(String email) {
        connection = dataBaseConnection.getConnection();
        PreparedStatement preparedStatement = null;
        User user = new User();
        try {
            preparedStatement = connection.prepareStatement(findByEmailSql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                setUserValues(user, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finallyClosing(preparedStatement, connection);
        }
        return user;
    }

    //Update
    /**
     * Find entity in User_Table with ID like in parameter
     * and write in data from parameter.
     *
     * @param entity the entity that need to update in DB.
     */
    @Override
    public void update(User entity) {
        connection = dataBaseConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(updateSql);
            setValuesInPreparedStatementWithoutId(preparedStatement, entity);
            preparedStatement.setLong(8, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finallyClosing(preparedStatement, connection);
        }
    }

    //Delete
    /**
     * Find entity in User_Table with ID like in parameter
     * and delete it from table in DB.
     *
     * @param entity the entity that need to remove from DB.
     */
    @Override
    public void remove(User entity) {
        connection = dataBaseConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finallyClosing(preparedStatement, connection);
        }
    }

    private void setUserValues(User user, ResultSet resultSet)
            throws SQLException {
        user.setId(resultSet.getLong("user_ID"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("user_password"));
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setBirthday(resultSet.getDate("birth_date"));
        user.setRoleId(resultSet.getLong("role_ID"));
    }

    private void setValuesInPreparedStatementWithoutId(PreparedStatement preparedStatement, User entity)
            throws SQLException {
        preparedStatement.setString(1, entity.getLogin());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.setString(3, entity.getEmail());
        preparedStatement.setString(4, entity.getFirstName());
        preparedStatement.setString(5, entity.getLastName());
        preparedStatement.setDate(6, entity.getBirthday());
        preparedStatement.setLong(7, entity.getRoleId());
    }
}
