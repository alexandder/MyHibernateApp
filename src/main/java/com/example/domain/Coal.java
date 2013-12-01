/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries ( {
    @NamedQuery(name = "coal.all", query = "Select c from Coal c"),
    @NamedQuery(name = "coal.byId", query = "Select c from Coal c where c.id=:id")
}

)
public class Coal implements Serializable {
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "NAME", unique = true, nullable = false)
    private String name;
    
    @Column(name = "TYPE")
    private String type;
    
    @Column(name = "PERCENTOFCARBON")
    private int percentOfCarbon;

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPercentOfCarbon() {
        return percentOfCarbon;
    }

    public void setPercentOfCarbon(int percentOfCarbon) {
        this.percentOfCarbon = percentOfCarbon;
    }
    
}
