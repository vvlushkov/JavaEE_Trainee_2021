package com.solution.lushkov.interfacesDaoPostgres;


import com.solution.lushkov.entity.User;

/**
 * Interface with additional CRUD methods for User.
 * Extends Dao.
 * @see Dao
 * @see User
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public interface UserDao extends Dao<User> {
    /**
     * Method to find entity of User with such {@code login}.
     *
     * @param login login of User
     * @return Entity of User with such {@code login}.
     */
    User findByLogin(String login);

    /**
     * Method to find entity of User with such {@code email}.
     *
     * @param email email of User.
     * @return Entity of User with such {@code email}.
     */
    User findByEmail(String email);
}
