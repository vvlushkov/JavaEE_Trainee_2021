package interfacesDaoPostgres;

import tables.Role;

/**
 * Interface with additional CRUD methods for Role.
 * Extends Dao.
 * @see Dao
 * @see Role
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public interface RoleDao extends Dao<Role> {
    /**
     * Method to find entity of Role with such {@code name}.
     *
     * @param name name of role.
     * @return Entity of Role with such {@code name}.
     */
    Role findByName(String name);
}
