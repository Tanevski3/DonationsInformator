/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donations.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "category")
public class Category implements Serializable {

    public Category() {
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;

    }
    private int id;
    private String name;

    /**
     * @return the id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue
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
     * @return the name
     */
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
