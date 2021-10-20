package entityDaoPostgres;

import dbConnection.DataBaseConnection;
import interfacesDaoPostgres.Dao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
     * Field to use logging functions.
     */
    private static final Logger LOG = LogManager
            .getLogger(GenericPostgresJdbcDao.class.getName());

    /**
     * Object of Connection that will be initialized in CRUD methods in DAO classes.
     */
    Connection connection;

    /**
     * Object of singleton DataBaseConnection.
     * Need to get the connections in DAO methods.
     * @see DataBaseConnection
     */
    DataBaseConnection dataBaseConnection;
    {
        try {
            dataBaseConnection = DataBaseConnection.getInstance();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Method close {@code PreparedStatement} and  {@code Connection},
     * that was used in DAO methods.
     * Methods executes in finally block in the CRUD methods.
     *
     * @param preparedStatement PreparedStatement object that need to close.
     * @param connection Connection object that need to close.
     */
    protected void finallyClosing(PreparedStatement preparedStatement,
                                Connection connection) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
