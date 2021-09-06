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
import java.util.stream.Collectors;

/**
 * Main class
 */
public class Main {
    /** URL of DB */
    private static final String url = "jdbc:postgresql://localhost:5432/nixHW";
    /** Username to log in DB */
    private static final String user = "postgres";
    /** Password to log in DB */
    private static final String password = "root";

    /**
     * Field to use logging functions.
     */
    private static final Logger LOG = LogManager
            .getLogger(Main.class.getName());

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
        UserDao userDao = daoFactory.getUserDao();
        RoleDao roleDao = daoFactory.getRoleDao();

        Role role1 = new Role();
        User user1 = new User();

        Dropper.main(null);
        Creator.main(null);



        //Пример работы на таблице ролей
        role1.setId(1L);
        role1.setName("Admin");
        roleDao.create(role1);
        role1.setId(2L);
        role1.setName("Non Admin");
        roleDao.create(role1);

        LOG.info("After creating entities:");
        List<Role> roleList = roleDao.findAll();
        LOG.info(roleList.toString());

        role1.setId(2L);
        role1.setName("Customer");
        roleDao.update(role1);
        LOG.info("After updating entity with ID = 2:");
        roleList = roleDao.findAll();
        LOG.info(roleList.toString());

        LOG.info("Entity with name = 'Admin':");
        LOG.info(roleDao.findByName("Admin"));
        LOG.info("Entity with ID = 2:");
        LOG.info(roleDao.findById(2L));

        roleDao.remove(role1);
        LOG.info("After removing last entity:");
        roleList = roleDao.findAll();
        LOG.info(roleList.toString());
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
}
