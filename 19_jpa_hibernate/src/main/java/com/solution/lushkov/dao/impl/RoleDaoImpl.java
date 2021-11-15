package com.solution.lushkov.dao.impl;

import com.solution.lushkov.util.HibernateUtil;
import com.solution.lushkov.dao.RoleDao;
import com.solution.lushkov.entity.Role;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Class with realized CRUD methods for table Role.
 * Extends GenericJdbcDao and implements RoleDao.
 * @see Role
 * @see AbstractDaoImpl
 * @see RoleDao
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class RoleDaoImpl extends AbstractDaoImpl<Role>
                                      implements RoleDao {
    /**
     * Field to use logging functions.
     */
    private static final Logger LOG = LogManager
            .getLogger(RoleDaoImpl.class.getName());

    //create
    /**
     * Creates tuple in table Role_Table in DB
     * by data from parameter {@code entity}.
     *
     * @param entity the entity that need to create in DB.
     */
    @Override
    public void create(Role entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.save(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    //read
    /**
     * Read and save in list all entities from Role_Table.
     *
     * @return list of all entities from Role_Table.
     */
    @Override
    public List<Role> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Role> rolesList = new ArrayList<>();
        try {
            rolesList = session.createQuery("FROM Role").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rolesList;
    }

    /**
     * Find and return entity with such ID in Role_Table.
     *
     * @param id the ID of entity.
     * @return entity with ID like in parameter.
     */
    @Override
    public Role findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Role role = new Role();
        try {
            role = session.get(Role.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return role;
    }

    /**
     * Find and return entity with such name in Role_Table.
     *
     * @param name name of role.
     * @return entity with name like in parameter.
     */
    @Override
    public Role findByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Role role = null;
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Role> roleCriteriaQuery = criteriaBuilder.createQuery(Role.class);
            Root<Role> roleRoot = roleCriteriaQuery.from(Role.class);
            roleCriteriaQuery.select(roleRoot).where(criteriaBuilder.equal(roleRoot.get("role_name"), name));
            Query<Role> query = session.createQuery(roleCriteriaQuery);
            role = query.getSingleResult();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return role;
    }

    //update
    /**
     * Find entity in Role_Table with ID like in parameter
     * and write in data from parameter.
     *
     * @param entity the entity that need to update in DB.
     */
    @Override
    public void update(Role entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    //delete
    /**
     * Find entity in Role_Table with ID like in parameter
     * and delete it from table in DB.
     *
     * @param entity the entity that need to remove from DB.
     */
    @Override
    public void remove(Role entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.delete(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
