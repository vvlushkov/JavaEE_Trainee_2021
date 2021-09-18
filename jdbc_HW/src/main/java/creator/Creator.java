package creator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * Class to create tables in our DB.
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class Creator {
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
    private static final String SQL_TABLES_CREATOR = "CREATE TABLE" +
            " IF NOT EXISTS public.`ROLE_TABLE`\n" +
            "(\n" +
            "    `role_ID` SERIAL PRIMARY KEY,\n" +
            "    `role_name` VARCHAR(100) NOT NULL\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE IF NOT EXISTS USER_TABLE\n" +
            "(\n" +
            "    user_ID SERIAL PRIMARY KEY,\n" +
            "    login VARCHAR(20) NOT NULL UNIQUE,\n" +
            "\tuser_password VARCHAR(100) NOT NULL,\n" +
            "    email VARCHAR(100) NOT NULL,\n" +
            "\tfirstName VARCHAR(100) NOT NULL,\n" +
            "\tlastName VARCHAR(100) NOT NULL,\n" +
            "    birth_date DATE NOT NULL,\n" +
            "\trole_ID INT NOT NULL,\n" +
            "\tFOREIGN KEY (role_ID) REFERENCES ROLE_TABLE (role_ID)\n" +
            ");";


    /**
     * Starting point of Creator. Executes {@code createTables} method.
     *
     * @param args console arguments.
     */
    public static void main(String[] args) {
        Creator creator = new Creator();
        creator.createTables();
        LOG.info("Tables in DB were created.");


    }

    /**
     * Method that creates tables in DB using {@code Statement.executeUpdate}
     */
    public void createTables() {
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
