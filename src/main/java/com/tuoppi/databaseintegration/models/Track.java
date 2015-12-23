package com.tuoppi.databaseintegration.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tuoppi
 */
@Entity
@XmlRootElement
public class Track implements Serializable {
    
    private int id;
    private String name;
    private double duration;
    

    public Track() {
    }

    public Track(String name, double duration) {
        this.name = name;
        this.duration = duration;
    }

    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
    
}