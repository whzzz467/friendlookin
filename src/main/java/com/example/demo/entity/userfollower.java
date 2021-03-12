package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="userfollower")
@JsonIgnoreProperties(value ={"hibernatelazyInitializer" })
public class userfollower {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "follower")
    private user u;
    public void setU(user u) {
        this.u = u;
    }
    public user getU() {
        return u;
    }
    @ManyToOne
    @JoinColumn(name = "befollower")
    private user bu;
    public void setBu(user bu) {
        this.bu = bu;
    }
    public user getBu() {
        return bu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
