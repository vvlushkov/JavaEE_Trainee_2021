import java.io.InputStream;
import java.sql.Date;
import java.util.List;

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

import static org.junit.Assert.assertEquals;

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


    @Test
    public void roleCreateTest() throws Exception {
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread()
                      .getContextClassLoader()
                      .getResourceAsStream("expectedCreate.xml"));
        IDataSet actualData = dbTester.getConnection().createDataSet();
        Role role = new Role();
        role.setName("Admin3");
        role.setId(3L);
        roleDao.create(role);
        //без создания сущности выше результат теста будет отрицательным
        Assertion.assertEquals(expectedData.getTable("ROLE_TABLE"), actualData.getTable("ROLE_TABLE"));
    }

    @Test
    public void roleReadTest() throws Exception {
        //тестировка считывания данных. Зависит от содержания data.xml
        List<Role> list = roleDao.findAll();
        assertEquals(2, list.size());
        assertEquals(false, list.isEmpty());
        assertEquals("Admin", list.get(0).getName());
        assertEquals("Customer", list.get(1).getName());
        assertEquals((Long)1L, list.get(0).getId());
        assertEquals((Long)2L, list.get(1).getId());
        Role role = roleDao.findById(1L);
        assertEquals("Admin", role.getName());
        role = roleDao.findByName("Customer");
        assertEquals((Long)2L, role.getId());
    }

    @Test
    public void roleUpdateTest() throws Exception {
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream("expectedUpdate.xml"));
        IDataSet actualData = dbTester.getConnection().createDataSet();

        Role role = new Role();
        role.setId(2L);
        role.setName("Customers");
        roleDao.update(role);
        //без апдейта выше результат теста будет отрицательным
        Assertion.assertEquals(expectedData.getTable("ROLE_TABLE"), actualData.getTable("ROLE_TABLE"));
    }

    @Test
    public void roleDeleteTest() throws Exception {
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream("expectedDelete.xml"));
        IDataSet actualData = dbTester.getConnection().createDataSet();

        Role role = new Role();
        role.setId(2L);
        roleDao.remove(role);
        //без удаления выше результат теста будет отрицательным
        Assertion.assertEquals(expectedData.getTable("ROLE_TABLE"), actualData.getTable("ROLE_TABLE"));
    }


    @Test
    public void userCreateTest() throws Exception {
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream("expectedCreate.xml"));
        IDataSet actualData = dbTester.getConnection().createDataSet();

        User user = new User();
        user.setId(3L);
        user.setLogin("tester");
        user.setPassword("tester");
        user.setEmail("tester@mail.org");
        user.setFirstName("Tester");
        user.setLastName("Tester");
        Date date = Date.valueOf("2001-01-01");
        user.setBirthday(date);
        user.setRoleId(1L);
        userDao.create(user);
        //после создания сущности выше результат теста будет положительным
        Assertion.assertEquals(expectedData.getTable("USER_TABLE"), actualData.getTable("USER_TABLE"));

    }

    @Test
    public void userReadTest() throws Exception {
        //тестировка считывания данных. Зависит от содержания data.xml
        List<User> list = userDao.findAll();
        assertEquals(2, list.size());
        assertEquals(false, list.isEmpty());
        assertEquals("Alex", list.get(0).getFirstName());
        assertEquals("Ivanova", list.get(1).getLastName());
        assertEquals((Long)1L, list.get(0).getId());
        assertEquals((Long)2L, list.get(1).getId());
        User user = userDao.findByLogin("alex007");
        assertEquals("alex007", user.getLogin());
        user = userDao.findByEmail("masha@gmail.com");
        assertEquals("masha@gmail.com", user.getEmail());
        user = userDao.findById(1L);
        assertEquals("Alex", user.getFirstName());
    }

    @Test
    public void userUpdateTest() throws Exception {
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream("expectedUpdate.xml"));
        IDataSet actualData = dbTester.getConnection().createDataSet();

        User user = new User();
        user.setId(1L);
        user.setLogin("alex007");
        user.setPassword("qwerty007");
        user.setEmail("alex007@gmail.com");
        user.setFirstName("Alex");
        user.setLastName("Petrov");
        Date date = Date.valueOf("2004-11-01");
        user.setBirthday(date);
        user.setRoleId(2L);
        userDao.update(user);
        //после апдейта выше результат теста будет положительным
        //(меняется роль и фамилия)
        Assertion.assertEquals(expectedData.getTable("USER_TABLE"), actualData.getTable("USER_TABLE"));
    }

    @Test
    public void userDeleteTest() throws Exception {
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream("expectedDelete.xml"));
        IDataSet actualData = dbTester.getConnection().createDataSet();

        User user = new User();
        user.setId(2L);
        userDao.remove(user);
        //после удаления выше результат теста будет положительным
        Assertion.assertEquals(expectedData.getTable("USER_TABLE"), actualData.getTable("USER_TABLE"));
    }

}
