package com.tuoppi.databaseintegration.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Tuoppi
 */
@Entity
@XmlRootElement
public class Artist implements Serializable {
    
    private int id;
    private String name;
    private List<Album> albums;
    
    
    public Artist() {
    }

    public Artist(String name) {
        this.name = name;
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

    @ManyToMany(mappedBy = "artists")
    @LazyCollection(LazyCollectionOption.FALSE) // Must be FALSE to access data after session is closed
    @Cascade(CascadeType.ALL) // Persist references too, if transient or detached
    @XmlTransient // Must be transient to avod Cyclical XML
    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = new ArrayList<>(albums);
    }
    
    public void addAlbum(Album album) {
        albums.add(album);
    }
    
    public void removeAlbum(Album album) {
        albums.remove(album);
    }
    
}