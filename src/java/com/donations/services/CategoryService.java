package com.donations.services;

import com.donations.model.Category;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;

/**
 *
 * @author user
 */
public class CategoryService {

    private Session session;
    private Transaction transaction;
    private SessionFactory sessionFactory;

    //LIST Categories
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        this.transaction = null;
        String sqlSelect1 = "FROM Category as category";

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query1 = this.session.createQuery(sqlSelect1);
            categories = query1.list();
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
        return categories;
    }

    public Boolean addCategory(Category category) {
        Boolean added = false;
        if (category == null) {
            return added;
        }
        this.transaction = null;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            this.session.save(category);
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

    public Category findCategoryById(Integer id) {

        Category category = null;

        this.transaction = null;
        // 

        String sqlSelect = "FROM Category as category WHERE category.id=" + id;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query = this.session.createQuery(sqlSelect);
            category = (Category) query.uniqueResult();
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

        return category;
    }

    public Boolean deleteCategory(Category category) {
        Boolean deleted = false;
        if (category == null) {
            return deleted;
        }
        this.transaction = null;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            this.session.delete(category);
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

    public Boolean updateCategory(Category category) {
        Boolean updated = false;
        if (category == null) {
            return updated;
        }
        this.transaction = null;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            this.session.update(category);
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
}
