package com.donations.services;

import com.donations.model.Type;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author user
 */
public class TypeService {

    private Transaction transaction;
    private Session session;
    private SessionFactory sessionFactory;

    public TypeService() {
    }

    public Boolean addType(Type type) {
        Boolean added = false;
        if (type == null) {
            return false;
        }
        this.transaction = null;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            this.session.save(type);
            this.transaction.commit();
            added = this.transaction.wasCommitted() ? true : false;
        } catch (RuntimeException e1) {

            if ((null != this.transaction) && this.transaction.isActive()) {
                try {
                    this.transaction.rollback();
                } catch (HibernateException e2) {
                    added = false;
                }
            }
        } finally {
            this.session.close();
            this.sessionFactory.close();
        }
        return added;
    }

    public List<Type> getAllTypes() {
        List<Type> types = new ArrayList<>();

        this.transaction = null;
        String sqlSelect1 = "FROM Type as type";

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query1 = this.session.createQuery(sqlSelect1);
            types = query1.list();
            this.transaction.commit();
        } catch (HibernateException e) {
            if ((null != this.transaction) && this.transaction.isActive()) {
                try {
                    this.transaction.rollback();
                } catch (HibernateException e2) {
                }
            }
        } finally {
            this.session.close();
            this.sessionFactory.close();
        }
        return types;
    }

    public Type findTypeById(Integer id) {

        Type type = null;

        this.transaction = null;
        // 

        String sqlSelect = "FROM Type as type WHERE type.id=" + id;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query = this.session.createQuery(sqlSelect);
            type = (Type) query.uniqueResult();
            this.transaction.commit();

        } catch (HibernateException e) {
            if ((null != this.transaction) && this.transaction.isActive()) {
                try {
                    this.transaction.rollback();
                } catch (HibernateException e2) {
                }
            }
        } finally {
            this.session.close();
            this.sessionFactory.close();
        }

        return type;
    }

    public Boolean updateType(Type type) {
        Boolean updated = false;
        if (type == null) {
            return false;
        }
        this.transaction = null;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            this.session.update(type);
            this.transaction.commit();
            updated = this.transaction.wasCommitted() ? true : false;
        } catch (RuntimeException e1) {

            if ((null != this.transaction) && this.transaction.isActive()) {
                try {
                    this.transaction.rollback();
                } catch (HibernateException e2) {
                    updated = false;
                }
            }
        } finally {
            this.session.close();
            this.sessionFactory.close();
        }
        return updated;
    }

    public Boolean deleteType(Type type) {
        Boolean deleted = false;
        if (type == null) {
            return false;
        }
        this.transaction = null;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            this.session.delete(type);
            this.transaction.commit();
            deleted = this.transaction.wasCommitted() ? true : false;
        } catch (RuntimeException e1) {
            if ((null != this.transaction) && this.transaction.isActive()) {
                try {
                    this.transaction.rollback();
                } catch (HibernateException e2) {
                    deleted = false;
                }
            }
        } finally {
            this.session.close();
            this.sessionFactory.close();
        }
        return deleted;
    }
}
