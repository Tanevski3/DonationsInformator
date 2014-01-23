/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donations.services;

import com.donations.model.Phone;
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
 * @author Alce
 */
public class PhoneService {

    private Session session;
    private Transaction transaction;
    private SessionFactory sessionFactory;

    public List<Phone> getAllPhonesForDonation(String id) {
        List<Phone> phones = new ArrayList<Phone>();

        this.transaction = null;
        String sqlSelect1 = "from Phone as phone where phone.donation.id=" + id;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query1 = this.session.createQuery(sqlSelect1);
            phones = query1.list();
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
        return phones;
    }

    public void addPhone(Phone phone) {
        this.transaction = null;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            this.session.save(phone);
            this.transaction.commit();
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
    }

    public void updatePhone(Phone phone) {
        this.transaction = null;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            this.session.update(phone);
            this.transaction.commit();
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
    }

    public void deletePhone(Phone phone) {
        this.transaction = null;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            this.session.delete(phone);
            this.transaction.commit();
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
    }
}
