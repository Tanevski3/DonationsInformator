package com.donations.services;

import com.donations.model.Donation;
import com.donations.model.User;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
 * @author nbuser
 */
public class UserService {

    private Session session;
    private Transaction transaction;
    private SessionFactory sessionFactory;

    public UserService() {
    }

    public User getUserById(Integer id) {
        User user = null;

        this.transaction = null;
        String sqlSelect = "FROM User as user WHERE user.id=" + id;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query = this.session.createQuery(sqlSelect);
            user = (User) query.uniqueResult();
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

        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        this.transaction = null;
        String sqlSelect1 = "FROM User as user";

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query1 = this.session.createQuery(sqlSelect1);
            users = query1.list();
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
        return users;
    }

    public Boolean updateUser(User user) {
        Boolean valid = false;
        if (user == null) {
            return valid;
        }
        this.transaction = null;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            this.session.update(user);
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

    public void deleteUser(User user) {

        this.transaction = null;


        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            this.session.delete(user);
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

   

    public Boolean addUser(User user) {

        Boolean valid = false;
        if (user == null) {
            return valid;
        }
        this.transaction = null;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            this.session.save(user);
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

    public User findUser(String username, String password) {

        User user = null;

        this.transaction = null;
        // 
        String sqlSelect = "FROM User user WHERE user.username='" + username + "' AND user.password='" + password + "' AND user.isActive=1";

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query = this.session.createQuery(sqlSelect);
            user = (User) query.uniqueResult();
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

        return user;
    }

    public User findUserByUsername(String username) {

        User user = null;

        this.transaction = null;
        // 

        String sqlSelect = "FROM User as user WHERE user.username=" + username;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query = this.session.createQuery(sqlSelect);
            user = (User) query.uniqueResult();
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

        return user;
    }

    public void deleteUserById(Integer id) {

        this.transaction = null;
        User user = null;
        String sqlSelect1 = "delete from User as user where user.id='" + id + "'";
        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query1 = this.session.createQuery(sqlSelect1);
            int q = query1.executeUpdate();
            System.out.println(q);
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

    public String getMD5(final String data) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(data.getBytes());
            BigInteger bigInt = new BigInteger(1, m.digest());
            String hashtext = bigInt.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}