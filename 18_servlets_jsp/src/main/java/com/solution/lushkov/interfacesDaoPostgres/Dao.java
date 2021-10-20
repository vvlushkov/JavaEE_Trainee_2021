package com.solution.lushkov.interfacesDaoPostgres;

import java.util.List;

/**
 * Interface with CRUD methods.
 *
 * @param <E> class of entity.
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public interface Dao<E> {
    /**
     * Method to create new entity in DB in such table.
     *
     * @param entity the entity that need to create in DB.
     */
    void create(E entity);

    /**
     * Method to update entity in DB in such table.
     *
     * @param entity the entity that need to update in DB.
     */
    void update(E entity);

    /**
     * Method to remove entity from DB from such table.
     *
     * @param entity the entity that need to remove from DB.
     */
    void remove(E entity);

    /**
     * Method to find and read in list all entities from such table in DB.
     *
     * @return list of all entities from such table
     */
    List<E> findAll();

    /**
     * Method to find the entity by its ID.
     *
     * @param id the ID of entity.
     * @return the entity from such table from DB.
     */
    E findById(Long id);
}
