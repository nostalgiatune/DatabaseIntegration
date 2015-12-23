
package com.tuoppi.databaseintegration.persistence;

import com.tuoppi.databaseintegration.utility.HibernateUtils;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tuoppi
 * 
 * Data access abstraction layer. Every database operation must go through this
 * class. Current implementation uses Hibernate.
 */
@Repository
public class EntityDAO {
    
    private final HibernateUtils hibernateUtils;
    
    @Autowired
    public EntityDAO(HibernateUtils hibernateUtils) {
        this.hibernateUtils = hibernateUtils;
    }
    
    public <T> List<T> getAllEntities(Class<T> clazz) throws HibernateException {
        
        Session session = hibernateUtils.getSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(clazz);
            List<T> entities = criteria.list();
            tx.commit();

            return entities;
        }
        catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            throw e;
        }
        finally {
            session.close();
        }
    }
    
    public <T> Optional<T> getEntityById(Class<T> clazz, Serializable id)
            throws HibernateException {
        
        Session session = hibernateUtils.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            T entity = (T) session.get(clazz, id);
            tx.commit();

            return Optional.ofNullable(entity);
        }
        catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            throw e;
        }
        finally {
            session.close();
        }
    }
    
    public <T> T saveOrUpdate(T entity) throws HibernateException {
        
        Session session = hibernateUtils.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(entity);
            tx.commit();
            return entity;
        }
        catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            throw e;
        }
        finally {
            session.close();
        }
    }
    
    public <T> void detete(T entity) throws HibernateException {
        
        Session session = hibernateUtils.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(entity);
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            throw e;
        }
        finally {
            session.close();
        }
    }
    
}