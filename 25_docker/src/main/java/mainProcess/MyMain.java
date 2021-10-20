package mainProcess;

import creator.Creator;
import daoFactory.DaoFactory;
import daoFactory.PostgresDaoFactory;
import dropper.Dropper;
import interfacesDaoPostgres.RoleDao;
import interfacesDaoPostgres.UserDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import tables.Role;
import tables.User;
import java.util.List;

/**
 * Main class
 */
public class MyMain {
//    /** URL of DB */
//    private static final String url = "jdbc:postgresql://localhost:5432/nixHW";
//    /** Username to log in DB */
//    private static final String user = "postgres";
//    /** Password to log in DB */
//    private static final String password = "root";

    /**
     * Field to use logging functions.
     */
    private static final Logger LOG = LogManager
            .getLogger(MyMain.class.getName());

    /**
     * Starter point. Show how CRUD methods works.
     *
     * @param args console arguments
     */
    public static void main(String[] args) {
        System.setProperty("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "WARNING");
        System.setProperty("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");

        String storage = "Postgres";
        DaoFactory daoFactory = createDaoFactoryByResource(storage);
        RoleDao roleDao = daoFactory.getRoleDao();
        UserDao userDao = daoFactory.getUserDao();

        Dropper.main(null);
        Creator.main(null);

        //работа с ролями
        roleDemo(roleDao);

        //работа с юзерами
        userDemo(userDao);
    }

    /**
     * Creating DAO Factories by the parameter {@code storage}.
     *
     * @param storage the name of storage which will be using.
     * @return DaoFactory object - Factory to our task
     */
    static DaoFactory createDaoFactoryByResource(String storage) {
        if(storage.equalsIgnoreCase("postgres")) {
            return new PostgresDaoFactory();
        } else {
            throw new RuntimeException(storage + " is unknown resource/storage.");
        }
    }

    /**
     * Class demonstrate how CRUD methods of RoleDao are working.
     *
     * @param roleDao object of class that realize DAO methods for Roles.
     */
    private static void roleDemo(RoleDao roleDao) {
        Role role = new Role();
        LOG.info("Working with roles:");
        role.setId(1L);
        role.setName("Admin");
        roleDao.create(role);
        role.setId(2L);
        role.setName("Absolutely Not Admin");
        roleDao.create(role);
        role.setId(3L);
        role.setName("Someone");
        roleDao.create(role);
        LOG.info("After creating role entities:");
        List<Role> roleList = roleDao.findAll();
        LOG.info(roleList.toString());
        role.setId(2L);
        role.setName("Customer");
        roleDao.update(role);
        LOG.info("After updating role entity with ID = 2:");
        roleList = roleDao.findAll();
        LOG.info(roleList.toString());
        LOG.info("Role entity with name = 'Admin':");
        LOG.info(roleDao.findByName("Admin"));
        LOG.info("Role entity with ID = 2:");
        LOG.info(roleDao.findById(2L));
        role.setId(3L);
        roleDao.remove(role);
        LOG.info("After removing role entity with ID = 3:");
        roleList = roleDao.findAll();
        LOG.info(roleList.toString());
    }

    /**
     * Class demonstrate how CRUD methods of UserDao are working.
     *
     * @param userDao object of class that realize DAO methods for Users.
     */
    private static void userDemo(UserDao userDao) {
        LOG.info("Working with users:");
        User user1 = new User(1L, "alex007", "qwerty007", "alex007@gmail.com",
                "Alex", "Agent", "2004-11-01", 1L);
        userDao.create(user1);
        User user2 = new User(2L, "masha_kasha", "mashkakashka", "masha@gmail.com",
                "Maria", "Ivanova", "2005-07-30", 2L);
        userDao.create(user2);
        LOG.info("After creating user entities:");
        List<User> userList = userDao.findAll();
        for (User userInList : userList) {
            LOG.info("User #" + userInList.getId() + ": " + userInList);
        }
        user1.setFirstName("Alexandr");
        user1.setLastName("Petrov");
        userDao.update(user1);
        LOG.info("After updating user entity with ID = 1:");
        LOG.info(userDao.findById(1L));
        LOG.info("User entity with login = 'alex007':");
        LOG.info(userDao.findByLogin("alex007"));
        LOG.info("User entity with Email = masha@gmail.com:");
        LOG.info(userDao.findByEmail("masha@gmail.com"));
        userDao.remove(user2);
        LOG.info("After removing user entity with ID = 2:");
        userList = userDao.findAll();
        for (User userInList : userList) {
            LOG.info("User #" + userInList.getId() + ": " + userInList);
        }
    }
}
