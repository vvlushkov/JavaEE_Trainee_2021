package dbConnection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import creator.Creator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Single tone DB connection manager with Connection Pool.
 */
public class DataBaseConnection {
    /** Resource bundle to file with properties of DB. */
    private static final ResourceBundle resource = ResourceBundle.getBundle("jdbc");
    /** URL of DB */
    private static final String URL = resource.getString("db.url");
    /** Username to log in DB */
    private static final String USERNAME = resource.getString("db.username");
    /** Password to log in DB */
    private static final String PASSWORD = resource.getString("db.password");

    private static DataBaseConnection instance;

    private DataBaseConnection() { }

    private static final ComboPooledDataSource CPDS = new ComboPooledDataSource();

    static {
        try {
            CPDS.setDriverClass("org.h2.Driver");
        } catch (PropertyVetoException e) {
            System.out.println("LALALALALALALALALALALALALALALALALALALALALALALALALALALALALALALALALALALALALALALALA");
            e.printStackTrace();
        }
        CPDS.setJdbcUrl(URL);
        CPDS.setUser(USERNAME);
        CPDS.setPassword(PASSWORD);
    }

    /**
     * @return connection from Connection Pool.
     * @throws SQLException some SQL exceptions.
     */
    public static Connection getConnection() {
        try {
            return CPDS.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Single tone realisation.
     *
     * @return instance of DataBaseConnection.
     * @throws SQLException some SQL exceptions.
     */
    public static DataBaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataBaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DataBaseConnection();
        }
        return instance;
    }
}
