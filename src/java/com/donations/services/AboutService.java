package com.donations.services;

import com.donations.model.About;
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
public class AboutService {

    private Session session;
    private Transaction transaction;
    private SessionFactory sessionFactory;

    public List<About> getAbout() {

        List<About> abouts = new ArrayList<>();

        this.transaction = null;
        String sqlSelect1 = "FROM About as about";

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query1 = this.session.createQuery(sqlSelect1);
            abouts = query1.list();
            this.transaction.commit();
        } catch (HibernateException e) {
            if ((null != this.transaction) && this.transaction.isActive()) {
                try {
                    this.transaction.rollback();
                } catch (HibernateException e2) {
                }
            }
            System.out.println(e.getMessage());
        } finally {
            this.session.close();
            this.sessionFactory.close();
        }
        return abouts;
    }

    public About getAboutById(Integer id) {
        About about = null;

        this.transaction = null;
        String sqlSelect = "FROM About as about WHERE about.id=" + id;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query = this.session.createQuery(sqlSelect);
            about = (About) query.uniqueResult();
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

        return about;
    }

    public Boolean updateAbout(About about) {
        Boolean valid = false;
        if (about == null) {
            return valid;
        }
        this.transaction = null;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            this.session.update(about);
            this.transaction.commit();
            if (this.transaction.wasCommitted()) {
                valid = true;
            } else {
                valid = false;
            }
        } catch (RuntimeException e1) {

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
        return valid;
    }
}
