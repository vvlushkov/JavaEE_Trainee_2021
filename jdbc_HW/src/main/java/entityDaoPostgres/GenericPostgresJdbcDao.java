package entityDaoPostgres;

import dbConnection.DataBaseConnection;
import interfacesDaoPostgres.Dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Abstract generic class with DAO functional.
 * Implements interface Dao without methods realization.
 * @see Dao ;
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public abstract class GenericPostgresJdbcDao<E> implements Dao<E> {
    /**
     * Method initialize {@code connection} with single tone DataBase manager.
     * @see DataBaseConnection
     *
     * @param connection the connection to DB that need to initialize.
     */
    public Connection setConnection(Connection connection) {
        {
            try {
                connection = DataBaseConnection.getInstance().getConnection();
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}
