import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import creator.Creator;
import entityDaoPostgres.JdbcPostgresRoleDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dbunit.*;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tables.Role;

public class DataSetTest {
    private final JdbcPostgresRoleDao roleDao = new JdbcPostgresRoleDao();

    private IDatabaseTester dbTester;

//    /** URL of DB */
//    private static final String url = "jdbc:h2:mem:test";
//    /** Username to log in DB */
//    private static final String user = "sa";
//    /** Password to log in DB */
//    private static final String password = "";
//    private static final String SQL_TABLES_CREATOR =
//            "DROP TABLE IF EXISTS USER_TABLE;\n" +
//            "DROP TABLE IF EXISTS ROLE_TABLE;\n" +
//            "CREATE TABLE IF NOT EXISTS ROLE_TABLE\n" +
//            "(\n" +
//            "    role_ID SERIAL PRIMARY KEY,\n" +
//            "    role_name TEXT NOT NULL\n" +
//            ");\n" +
//            "\n" +
//            "CREATE TABLE USER_TABLE\n" +
//            "(\n" +
//            "    user_ID SERIAL PRIMARY KEY,\n" +
//            "    login VARCHAR(20) NOT NULL,\n" +
//            "\tuser_password TEXT NOT NULL,\n" +
//            "    email TEXT NOT NULL,\n" +
//            "\tfirstName TEXT NOT NULL,\n" +
//            "\tlastName TEXT NOT NULL,\n" +
//            "    birth_date DATE NOT NULL,\n" +
//            "\trole_ID INT NOT NULL," +
//            "CONSTRAINT Unq_login UNIQUE (login),\n" +
//            "\tFOREIGN KEY (role_ID) REFERENCES ROLE_TABLE (role_ID)\n" +
//            ");";

//    public DataSetTest(String name)
//    {
//        super( name );
//        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, url);
//        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, user);
//        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, password);
//        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "public");
//    }

    @BeforeClass
    public static void createSchema() throws Exception {
        Creator.main(null);
    }

    @Before
    public void initConfigs() throws Exception{
        dbTester = new JdbcDatabaseTester("org.h2.Driver", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        dbTester.setDataSet(readDataSet());
        dbTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        dbTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
        dbTester.onSetup();
    }

    private IDataSet readDataSet() throws Exception {
        String fileName = "role.xml";
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream file = classLoader.getResourceAsStream(fileName);
        return new FlatXmlDataSetBuilder().build(file);
    }

    //Тут показываю что разобрался с датасетами
    //и сравниваю данные полученные из двух разных файлов
    @Test
    public void roleDaoCreateTest() throws Exception {
        Role role = new Role();
        role.setName("Admin3");
        role.setId(3L);
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread()
                      .getContextClassLoader()
                      .getResourceAsStream("expectedRole.xml"));
        roleDao.create(role);
        IDataSet actualData = dbTester.getConnection().createDataSet();
        Assertion.assertEquals(expectedData.getTable("ROLE_TABLE"), actualData.getTable("ROLE_TABLE"));
    }
}
