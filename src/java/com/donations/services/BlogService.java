package com.donations.services;

import com.donations.model.Blog;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;

public class BlogService {

    private Session session;
    private Transaction transaction;
    private SessionFactory sessionFactory;

    //LIST BLOGS
    public List<Blog> getAllBlogs() {
        List<Blog> blogs = new ArrayList<Blog>();

        this.transaction = null;
        String sqlSelect1 = "FROM Blog as blog";

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query1 = this.session.createQuery(sqlSelect1);
            blogs = query1.list();
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
        return blogs;
    }

    public List<Blog> getOrderedBlogsByPostDate() {

        List<Blog> blogs = new ArrayList<Blog>();

        this.transaction = null;
        //from Document fetch all properties order by name
        String sqlSelect1 = "from Blog as blog order by post_date desc";

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query1 = this.session.createQuery(sqlSelect1);
            blogs = query1.list();
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
        return blogs;
    }
    //ADD BLOG

    public void addBlog(Blog blog) {

        this.transaction = null;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            this.session.save(blog);
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

    //UPDATE BLOG
    public Blog findBlogByTitle(String title) {

        Blog blog = null;

        this.transaction = null;
        // 

        String sqlSelect = "FROM Blog as blog WHERE blog.title=" + title;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query = this.session.createQuery(sqlSelect);
            blog = (Blog) query.uniqueResult();
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

        return blog;
    }

    public void updateBlog(Blog blog) {
        this.transaction = null;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            this.session.update(blog);
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

    //DELETE BLOG
    public void deleteBlog(Blog blog) {
        this.transaction = null;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            this.session.delete(blog);
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

    public Blog findBlogById(Integer _id) {

        Blog _blog = null;

        this.transaction = null;
        // 

        String sqlSelect = "FROM Blog as blog WHERE blog.id=" + _id;

        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query = this.session.createQuery(sqlSelect);
            _blog = (Blog) query.uniqueResult();
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

        return _blog;
    }

    public List<Blog> getBlogsByTitleOrText(String _searchText) {
        List<Blog> blogs = new ArrayList<Blog>();

        this.transaction = null;
        String sqlSelect1 = "from Blog as b where b.title like '%" + _searchText + "%' or b.shortDescription like '%" + _searchText + "%'";
        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.transaction = this.session.beginTransaction();
            Query query1 = this.session.createQuery(sqlSelect1);
            blogs = query1.list();
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
        return blogs;
    }
}
