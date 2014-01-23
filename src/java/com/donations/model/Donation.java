/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donations.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author user
 */
@Entity
@Table(name = "donations")
public class Donation implements Serializable {

    private int id;
    private String title;
    private String donationUrl;
    private String description;
    private String imageSource;
    private Date startDate;
    private Date endDate;
    private int priority;
    private User user;
    private Category category;
    private Type type;
    private List<Phone> phones;
    private List<Account> accounts;
    private Boolean published;

    public Donation() {
    }

    public Donation(int id, String title, String donationUrl, String description,
            String imageSource, Date startDate, Date endDate, int priority, Boolean published,User user,
            Category category, Type type, List<Phone> phones, List<Account> accounts) {
        this.id = id;
        this.title = title;
        this.donationUrl = donationUrl;
        this.description = description;
        this.imageSource = imageSource;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
        this.user = user;
        this.category = category;
        this.type = type;
        this.phones = phones;
        this.accounts = accounts;
        this.published=published;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue()
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "donation_url")
    public String getDonationUrl() {
        return donationUrl;
    }

    public void setDonationUrl(String donationUrl) {
        this.donationUrl = donationUrl;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "image_source")
    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "start_date")
    @DateTimeFormat(pattern="YYYY-MM-DD")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "end_date")
    @DateTimeFormat(pattern="YYYY-MM-DD")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "priority")
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name = "type_id")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "donation", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "donation", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Column(name = "published")
    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }
}
