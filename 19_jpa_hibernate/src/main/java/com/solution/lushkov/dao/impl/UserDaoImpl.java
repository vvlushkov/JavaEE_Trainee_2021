package com.solution.lushkov.dao.impl;

import com.solution.lushkov.util.HibernateUtil;
import com.solution.lushkov.dao.UserDao;
import com.solution.lushkov.entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Class with realized CRUD methods for table User.
 * Extends GenericJdbcDao and implements UserDao.
 * @see User
 * @see AbstractDaoImpl
 * @see UserDao
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class UserDaoImpl extends AbstractDaoImpl<User>
                                      implements UserDao {
    /**
     * Field to use logging functions.
     */
    private static final Logger LOG = LogManager
            .getLogger(UserDaoImpl.class.getName());

    //Create
    /**
     * Creates tuple in table User_Table in DB
     * by data from parameter {@code entity}.
     *
     * @param entity the entity that need to create in DB.
     */
    @Override
    public void create(User entity) {
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

    //Read
    /**
     * Read and save in list all entities from User_Table.
     *
     * @return list of all entities from User_Table.
     */
    @Override
    public List<User> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<User> usersList = new ArrayList<>();
        try {
            usersList = session.createQuery("FROM User").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return usersList;
    }

    /**
     * Find and return entity with such ID in User_Table.
     *
     * @param id the ID of entity.
     * @return entity with ID like in parameter.
     */
    @Override
    public User findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = new User();
        try {
            user = session.get(User.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }

    /**
     * Find and return entity with such login in User_Table.
     *
     * @param login login of user.
     * @return entity with login like in parameter.
     */
    @Override
    public User findByLogin(String login) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = null;
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> roleCriteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> roleRoot = roleCriteriaQuery.from(User.class);
            roleCriteriaQuery.select(roleRoot).where(criteriaBuilder.equal(roleRoot.get("login"), login));
            Query<User> query = session.createQuery(roleCriteriaQuery);
            user = query.getSingleResult();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }

    /**
     * Find and return entity with such email in User_Table.
     *
     * @param email email of user.
     * @return entity with email like in parameter.
     */
    @Override
    public User findByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = null;
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> roleCriteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> roleRoot = roleCriteriaQuery.from(User.class);
            roleCriteriaQuery.select(roleRoot).where(criteriaBuilder.equal(roleRoot.get("email"), email));
            Query<User> query = session.createQuery(roleCriteriaQuery);
            user = query.getSingleResult();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }

    //Update
    /**
     * Find entity in User_Table with ID like in parameter
     * and write in data from parameter.
     *
     * @param entity the entity that need to update in DB.
     */
    @Override
    public void update(User entity) {
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

    //Delete
    /**
     * Find entity in User_Table with ID like in parameter
     * and delete it from table in DB.
     *
     * @param entity the entity that need to remove from DB.
     */
    @Override
    public void remove(User entity) {
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
