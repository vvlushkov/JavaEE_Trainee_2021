package com.solution.lushkov;

import com.solution.lushkov.entityDaoPostgres.JdbcPostgresRoleDao;
import com.solution.lushkov.entityDaoPostgres.JdbcPostgresUserDao;
import com.solution.lushkov.interfacesDaoPostgres.RoleDao;
import com.solution.lushkov.interfacesDaoPostgres.UserDao;
import com.solution.lushkov.tables.Role;
import com.solution.lushkov.tables.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.postgresql.Driver;

import java.util.List;

public class Main {
    /**
     * Field to use logging functions.
     */
    private static final Logger LOG = LogManager
            .getLogger(Main.class.getName());

    public static void main(String[] args) {

        System.setProperty("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "WARNING");
        System.setProperty("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");

        RoleDao roleDao = new JdbcPostgresRoleDao();
        UserDao userDao = new JdbcPostgresUserDao();

        LOG.info("After creating user entities:");
        List<User> userList = userDao.findAll();
        for (User userInList : userList) {
            LOG.info("User #" + userInList.getId() + ": " + userInList);
        }

        String className = Driver.class.getCanonicalName();
        System.out.println(className);

        try {
            Role role1 = new Role(1L, "Admin");
            roleDao.create(role1);
            Role role2 = new Role(2L, "User");
            roleDao.create(role2);

            User user1 = new User(1L, "admin", "password", "admin@email",
                    "Admin", "Admin", "2000-01-01", 1L);
            userDao.create(user1);
            User user2 = new User("user1", "password", "user@email",
                    "User", "User", "2000-01-01", 2L);
            userDao.create(user2);
            User user3 = new User("user2", "password", "user@email",
                    "User", "User", "2000-01-01", 2L);
            userDao.create(user3);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

    }
}
