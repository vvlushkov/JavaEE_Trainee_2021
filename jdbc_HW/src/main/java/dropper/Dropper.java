package dropper;

import creator.Creator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * Class to drop tables from our DB.
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class Dropper {
    /**
     * Field to use logging functions.
     */
    private static final Logger LOG = LogManager
            .getLogger(Creator.class.getName());
    /** Resource bundle to file with properties of DB. */
    private static final ResourceBundle resource = ResourceBundle.getBundle("jdbc");
    /** URL of DB */
    private static final String URL = resource.getString("db.url");
    /** Username to log in DB */
    private static final String USERNAME = resource.getString("db.username");
    /** Password to log in DB */
    private static final String PASSWORD = resource.getString("db.password");
    /** Field with our SQL query. */
    private static final String SQL_TABLES_CREATOR = "DROP TABLE" +
            " IF EXISTS public.\"User_Table\";\n" +
            "DROP TABLE IF EXISTS public.\"Role_Table\";";

    /**
     * Starting point of Dropper. Executes {@code dropTables} method.
     *
     * @param args console arguments.
     */
    public static void main(String[] args) {
        Dropper dropper = new Dropper();
        dropper.dropTables();
        LOG.info("Tables in DB were dropped.");
    }

    /**
     * Method that drops tables from DB using {@code Statement.executeUpdate}
     */
    public void dropTables() {
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL_TABLES_CREATOR);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            LOG.error("SQLState: " + e.getSQLState());
            LOG.error("Error Code: " + e.getErrorCode());
            LOG.error("Message: " + e.getMessage());
            for(Throwable throwable : e) {
                LOG.error(throwable);
            }
        }
    }

}
