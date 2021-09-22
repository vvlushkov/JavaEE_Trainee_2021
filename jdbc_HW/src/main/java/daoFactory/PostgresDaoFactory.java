package daoFactory;

import entityDaoPostgres.JdbcPostgresRoleDao;
import entityDaoPostgres.JdbcPostgresUserDao;
import interfacesDaoPostgres.RoleDao;
import interfacesDaoPostgres.UserDao;

/**
 * Abstract factory realisation for resource storage "Postgres"
 */
public class PostgresDaoFactory implements  DaoFactory {
    /**
     * @return object of JdbcPostgresRoleDao - RoleDao realisation for Postgres
     */
    @Override
    public RoleDao getRoleDao() {
        return new JdbcPostgresRoleDao();
    }

    /**
     * @return object of JdbcPostgresUserDao - UserDao realisation for Postgres
     */
    @Override
    public UserDao getUserDao() {
        return new JdbcPostgresUserDao();
    }
}
