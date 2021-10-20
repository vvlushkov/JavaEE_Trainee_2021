package daoFactory;

import interfacesDaoPostgres.RoleDao;
import interfacesDaoPostgres.UserDao;

/**
 * Interface of abstract factory.
 */
public interface DaoFactory {
    /** Need to return implementation of RoleDao by the chosen resource storage. */
    RoleDao getRoleDao();
    /** Need to return implementation of UserDao by the chosen resource storage. */
    UserDao getUserDao();
}
