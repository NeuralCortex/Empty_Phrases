/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fx.emp.hibernate.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author pscha
 */
@Entity
@Table(name = "part")
public class Part implements Serializable {

    private long id;
    private long part;
    private String phrase;

    public Part() {

    }

    public Part(long part, String phrase) {
        this.part = part;
        this.phrase = phrase;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPart() {
        return part;
    }

    public void setPart(long part) {
        this.part = part;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}
