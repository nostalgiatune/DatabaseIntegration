package com.tuoppi.databaseintegration.models;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tuoppi
 * 
 * Wrapper class containing all albums queried from database.
 * Needed to marshal / unmarshal list of albums.
 * Will be sent to client in XML format
 */
@XmlRootElement
public class FetchReply {
    
    private List<Album> albums;

    
    public FetchReply() {
    }

    public FetchReply(List<Album> albums) {
        this.albums = albums;
    }

    @XmlElementWrapper(name = "albums")
    @XmlElement(name = "album")
    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
    
}