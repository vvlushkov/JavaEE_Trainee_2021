import java.io.InputStream;
import java.sql.Date;
import creator.Creator;
import entityDaoPostgres.JdbcPostgresRoleDao;
import entityDaoPostgres.JdbcPostgresUserDao;
import org.dbunit.*;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tables.Role;
import tables.User;

public class DataSetTest {

    private final JdbcPostgresRoleDao roleDao = new JdbcPostgresRoleDao();
    private final JdbcPostgresUserDao userDao = new JdbcPostgresUserDao();

    private IDatabaseTester dbTester;


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
        String fileName = "data.xml";
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
                      .getResourceAsStream("expectedData.xml"));
        roleDao.create(role);
        IDataSet actualData = dbTester.getConnection().createDataSet();
        Assertion.assertEquals(expectedData.getTable("ROLE_TABLE"), actualData.getTable("ROLE_TABLE"));
    }

    //тест с апгрейдом данных
    @Test
    public void userUpdateTest() throws Exception {
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream("expectedData.xml"));
        IDataSet actualData = dbTester.getConnection().createDataSet();

        //сейчас данные таблиц юзеров разнятся, и результат теста будет отрицательный(разные айдишники ролей)
        //Создаю сущность которую передам в апдейт для исправления данныйх
        User user = new User();
        user.setId(1L);
        user.setLogin("alex007");
        user.setPassword("qwerty007");
        user.setEmail("alex007@gmail.com");
        user.setFirstName("Alex");
        user.setLastName("Agent");
        Date date = Date.valueOf("2004-11-01");
        user.setBirthday(date);
        user.setRoleId(2L);
        userDao.update(user);
        //после апдейта выше результат теста будет положительным
        Assertion.assertEquals(expectedData.getTable("USER_TABLE"), actualData.getTable("USER_TABLE"));
    }
}
