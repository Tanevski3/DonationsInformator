package com.donations.services;

import com.donations.model.Donation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;

public class DonationService {

    private Session session;
    private Transaction transaction;
    private SessionFactory sessionFactory;

    //LIST DONATIONS
    public List<Donation> getAllDonations() {
        List<Donation> donations = new ArrayList<Donation>();

        this.transaction = null;
        String sqlSelect1 = "FROM Donation as donation order by donation.priority desc";

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query1 = this.session.createQuery(sqlSelect1);
            donations = query1.list();
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
        return donations;
    }

    //ADD DONATION
    public void addDonation(Donation donation) {
        this.transaction = null;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            this.session.save(donation);
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

    //UPDATE DONATION
    public Donation findDonationByTitle(String title) {

        Donation donation = null;

        this.transaction = null;
        // 

        String sqlSelect = "FROM Donation as donation WHERE donation.title=" + title;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query = this.session.createQuery(sqlSelect);
            donation = (Donation) query.uniqueResult();
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

        return donation;
    }

    public void updateDonation(Donation donation) {
        this.transaction = null;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            this.session.update(donation);
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

    //DELETE DONATION
    public void deleteDonation(Donation donation) {
        this.transaction = null;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            this.session.delete(donation);
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

    public Donation findDonationbyId(Integer id) {

        Donation donation = null;

        this.transaction = null;
        // 

        String sqlSelect = "FROM Donation as donation WHERE donation.id=" + id;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query = this.session.createQuery(sqlSelect);
            donation = (Donation) query.uniqueResult();
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

        return donation;
    }

    public List<Donation> getDonationsByTitleOrText(String searchText) {
        List<Donation> donations = new ArrayList<Donation>();

        this.transaction = null;
        String sqlSelect = "FROM Donation as donation WHERE donation.title LIKE '%" + searchText + "%' OR donation.description LIKE '%" + searchText + "'";

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query1 = this.session.createQuery(sqlSelect);
            donations = query1.list();
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
        return donations;
    }

    public List<Donation> getAllOrderedDonations(String ordering) {
        List<Donation> donations = new ArrayList<Donation>();

        this.transaction = null;
        String sqlSelect1 = "FROM Donation as donation order by donation." + ordering;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query1 = this.session.createQuery(sqlSelect1);
            donations = query1.list();
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
        return donations;
    }

    public List<Donation> getAllOldDonations() {
        List<Donation> donations = new ArrayList<Donation>();

        this.transaction = null;
        Date endDate = new Date();
        String sqlSelect1 = "FROM Donation as donation WHERE donation.endDate < :endDate";

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query1 = this.session.createQuery(sqlSelect1);

            query1.setParameter("endDate", endDate);
            donations = query1.list();
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
        return donations;
    }

    public List<Donation> getNewDonations() {
        List<Donation> donations = new ArrayList<Donation>();

        Date endDate = new Date();
        this.transaction = null;
        String sqlSelect1 = "FROM Donation as donation WHERE donation.endDate >= :endDate and donation.published=1 order by donation.priority desc";

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query1 = this.session.createQuery(sqlSelect1);
            query1.setParameter("endDate", endDate);
            donations = query1.list();
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
        return donations;
    }
}
