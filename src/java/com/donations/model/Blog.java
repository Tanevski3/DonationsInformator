/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donations.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author user
 */
@Entity
@Table(name = "blog")
public class Blog {

    private int id;
    private String title;
    private String shortDescription;
    private String longDescription;
    private Date postDate;
    private User user;

    public Blog() {
        id = 0;
        title = null;
        shortDescription = null;
        longDescription = null;
        postDate = null;
        user = null;
    }

    public Blog(int id, String title, String shortDescription, String longDescription,
            Date postDate, User user) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.postDate = postDate;
        this.user = user;
    }

    /**
     * @return the id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue()
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the shortDescription
     */
    @Column(name = "short_description")
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * @param shortDescription the shortDescription to set
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * @return the longDescription
     */
    @Column(name = "long_description")
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * @param longDescription the longDescription to set
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    /**
     * @return the postDate
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "post_date")
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    public Date getPostDate() {
        return postDate;
    }

    /**
     * @param postDate the postDate to set
     */
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    /**
     * @return the user
     */
    @ManyToOne
    @JoinColumn(name = "blog_user_id")
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
}
