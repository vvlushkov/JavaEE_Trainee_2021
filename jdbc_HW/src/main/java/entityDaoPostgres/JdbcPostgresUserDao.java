package entityDaoPostgres;

import interfacesDaoPostgres.UserDao;
import tables.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class with realized CRUD methods for table User.
 * Extends GenericJdbcDao and implements UserDao.
 * @see User
 * @see GenericPostgresJdbcDao
 * @see UserDao
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class JdbcPostgresUserDao extends GenericPostgresJdbcDao<User> implements UserDao {
    /** Connection to DB. */

    //Create
    /**
     * Creates tuple in table User_Table in DB
     * by data from parameter {@code entity}.
     *
     * @param entity the entity that need to create in DB.
     */
    @Override
    public void create(User entity) {
        connection = getPoolConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO public.\"USER_TABLE\" (user_ID, " +
                "login, user_password, email, firstName, lastName, " +
                "birth_date, role_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getLogin());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, entity.getFirstName());
            preparedStatement.setString(6, entity.getLastName());
            preparedStatement.setDate(7, entity.getBirthday());
            preparedStatement.setLong(8, entity.getRoleId());

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

    //Read
    /**
     * Read and save in list all entities from User_Table.
     *
     * @return list of all entities from User_Table.
     */
    @Override
    public List<User> findAll() {
        connection = getPoolConnection();
        List<User> usersList = new ArrayList<>();
        String sql = "SELECT * FROM public.\"USER_TABLE\";";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("user_ID"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("user_password"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setBirthday(resultSet.getDate("birth_date"));
                user.setRoleId(resultSet.getLong("role_ID"));
                usersList.add(user);
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
        connection = getPoolConnection();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM public.\"USER_TABLE\" WHERE user_ID = ?;";
        User user = new User();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                user.setId(resultSet.getLong("user_ID"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("user_password"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setBirthday(resultSet.getDate("birth_date"));
                user.setRoleId(resultSet.getLong("role_ID"));
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
        connection = getPoolConnection();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM public.\"USER_TABLE\" WHERE login = ?;";
        User user = new User();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                user.setId(resultSet.getLong("user_ID"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("user_password"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setBirthday(resultSet.getDate("birth_date"));
                user.setRoleId(resultSet.getLong("role_ID"));
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
        connection = getPoolConnection();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM public.\"USER_TABLE\" WHERE email = ?;";
        User user = new User();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                user.setId(resultSet.getLong("user_ID"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("user_password"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setBirthday(resultSet.getDate("birth_date"));
                user.setRoleId(resultSet.getLong("role_ID"));
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
        connection = getPoolConnection();
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE public.\"USER_TABLE\" SET login = ?, " +
                "user_password = ?, email = ?, firstName = ?, lastName = ?," +
                " birth_date = ?, role_ID = ? WHERE user_ID = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getFirstName());
            preparedStatement.setString(5, entity.getLastName());
            preparedStatement.setDate(6, entity.getBirthday());
            preparedStatement.setLong(7, entity.getRoleId());
            preparedStatement.setLong(8, entity.getId());

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

    //Delete
    /**
     * Find entity in User_Table with ID like in parameter
     * and delete it from table in DB.
     *
     * @param entity the entity that need to remove from DB.
     */
    @Override
    public void remove(User entity) {
        connection = getPoolConnection();
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM public.\"USER_TABLE\" WHERE user_ID = ?;";
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
