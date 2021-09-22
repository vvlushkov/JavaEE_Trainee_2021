package entityDaoPostgres;

import dropper.Dropper;
import interfacesDaoPostgres.RoleDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import tables.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class with realized CRUD methods for table Role.
 * Extends GenericJdbcDao and implements RoleDao.
 * @see Role
 * @see GenericPostgresJdbcDao
 * @see RoleDao
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class JdbcPostgresRoleDao extends GenericPostgresJdbcDao<Role> implements RoleDao {
    /** Connection to DB. */

    /**
     * Field to use logging functions.
     */
    private static final Logger LOG = LogManager
            .getLogger(JdbcPostgresRoleDao.class.getName());

    //create
    /**
     * Creates tuple in table Role_Table in DB
     * by data from parameter {@code entity}.
     *
     * @param entity the entity that need to create in DB.
     */
    @Override
    public void create(Role entity) {
        connection = getPoolConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO public.`ROLE_TABLE` (role_ID, " +
                "role_name) VALUES (?, ?);";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            LOG.error("SQLState: " + e.getSQLState());
            LOG.error("Error Code: " + e.getErrorCode());
            LOG.error("Message: " + e.getMessage());
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        connection = getPoolConnection();
        List<Role> rolesList = new ArrayList<>();
        String sql = "SELECT * FROM public.\"ROLE_TABLE\";";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getLong("role_ID"));
                role.setName(resultSet.getString("role_name"));
                rolesList.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        connection = getPoolConnection();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM public.\"ROLE_TABLE\" WHERE role_ID = ?;";
        Role role = new Role();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                role.setId(resultSet.getLong("role_ID"));
                role.setName(resultSet.getString("role_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        connection = getPoolConnection();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM public.\"ROLE_TABLE\" WHERE role_name = ?;";
        Role role = new Role();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                role.setId(resultSet.getLong("role_ID"));
                role.setName(resultSet.getString("role_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        connection = getPoolConnection();
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE public.\"ROLE_TABLE\" SET role_name = ?" +
                " WHERE role_ID = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setLong(2, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        connection = getPoolConnection();
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM public.\"ROLE_TABLE\" WHERE role_ID = ?;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
