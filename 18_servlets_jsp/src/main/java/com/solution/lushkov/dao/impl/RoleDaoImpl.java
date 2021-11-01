package com.solution.lushkov.dao.impl;

import com.solution.lushkov.dao.RoleDao;
import com.solution.lushkov.entity.Role;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class with realized CRUD methods for table Role.
 * Extends GenericJdbcDao and implements RoleDao.
 * @see Role
 * @see AbstractDaoImpl
 * @see RoleDao
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class RoleDaoImpl extends AbstractDaoImpl<Role> implements RoleDao {
    /**
     * Field to use logging functions.
     */
    private static final Logger LOG = LogManager
            .getLogger(RoleDaoImpl.class.getName());

    public static final String insertSql = "INSERT INTO role_table (role_ID, " +
            "role_name) VALUES (?, ?);";
    public static final String findAllSql = "SELECT * FROM role_table;";
    public static final String findByIdSql = "SELECT * FROM role_table WHERE role_ID = ?;";
    public static final String findByNameSql = "SELECT * FROM role_table WHERE role_name = ?;";
    public static final String updateSql = "UPDATE role_table SET role_name = ?" +
            " WHERE role_ID = ?";
    public static final String deleteSql = "DELETE FROM role_table WHERE role_ID = ?;";

    //create
    /**
     * Creates tuple in table Role_Table in DB
     * by data from parameter {@code entity}.
     *
     * @param entity the entity that need to create in DB.
     */
    @Override
    public void create(Role entity) {
        createConnectionAndChangeAutoCommit();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            finallyClosing(preparedStatement, connection);
        }
    }

    //read
    /**
     * Read and save in list all entities from Role_Table.
     *
     * @return list of all entities from Role_Table.
     */
    @Override
    public List<Role> findAll() {
        createConnectionAndChangeAutoCommit();
        List<Role> rolesList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(findAllSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getLong("role_ID"));
                role.setName(resultSet.getString("role_name"));
                rolesList.add(role);
            }
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            finallyClosing(preparedStatement, connection);
        }
        return rolesList;
    }

    /**
     * Find and return entity with such ID in Role_Table.
     *
     * @param id the ID of entity.
     * @return entity with ID like in parameter.
     */
    @Override
    public Role findById(Long id) {
        createConnectionAndChangeAutoCommit();
        PreparedStatement preparedStatement = null;
        Role role = new Role();
        try {
            preparedStatement = connection.prepareStatement(findByIdSql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                role.setId(resultSet.getLong("role_ID"));
                role.setName(resultSet.getString("role_name"));
            }
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            finallyClosing(preparedStatement, connection);
        }
        return role;
    }

    /**
     * Find and return entity with such name in Role_Table.
     *
     * @param name name of role.
     * @return entity with name like in parameter.
     */
    @Override
    public Role findByName(String name) {
        createConnectionAndChangeAutoCommit();
        PreparedStatement preparedStatement = null;
        Role role = new Role();
        try {
            preparedStatement = connection.prepareStatement(findByNameSql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                role.setId(resultSet.getLong("role_ID"));
                role.setName(resultSet.getString("role_name"));
            }
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            finallyClosing(preparedStatement, connection);
        }
        return role;
    }

    //update
    /**
     * Find entity in Role_Table with ID like in parameter
     * and write in data from parameter.
     *
     * @param entity the entity that need to update in DB.
     */
    @Override
    public void update(Role entity) {
        createConnectionAndChangeAutoCommit();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setLong(2, entity.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            finallyClosing(preparedStatement, connection);
        }
    }

    //delete
    /**
     * Find entity in Role_Table with ID like in parameter
     * and delete it from table in DB.
     *
     * @param entity the entity that need to remove from DB.
     */
    @Override
    public void remove(Role entity) {
        createConnectionAndChangeAutoCommit();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            finallyClosing(preparedStatement, connection);
        }
    }

    protected void createConnectionAndChangeAutoCommit() {
        connection = dataBaseConnection.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
