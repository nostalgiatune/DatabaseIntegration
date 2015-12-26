package com.tuoppi.databaseintegration.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
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
public class Album implements Serializable {
    
    private int id;
    private String name;
    private List<Artist> artists;
    private List<Track> tracks;
    

    public Album() {
    }

    public Album(String name, List<Artist> artists, List<Track> tracks) {
        
        this.name = name;
        this.artists = new ArrayList<>(artists);
        this.tracks = new ArrayList<>(tracks);
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

    @ManyToMany
    @Cascade(CascadeType.ALL) // Persist references too, if transient or detached
    @LazyCollection(LazyCollectionOption.FALSE) // Must be FALSE to access data after session is closed
    @XmlElementWrapper(name = "artists") // Optional for better XML structure
    @XmlElement(name = "artist")
    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
    
    @OneToMany
    @Cascade(CascadeType.ALL) // Persist references too, if transient or detached
    @LazyCollection(LazyCollectionOption.FALSE) // Must be FALSE to access data after session is closed
    @XmlElementWrapper(name = "tracks") // Optional for better XML structure
    @XmlElement(name = "track")
    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
    
    public void addArtist(Artist artist) {
        artists.add(artist);
    }
    
    public void removeArtist(Artist artist) {
        artists.remove(artist);
    }
    
    public void addTrack(Track track) {
        tracks.add(track);
    }
    
    public void removeTrack(Track track) {
        tracks.remove(track);
    }
    
}