package com.tuoppi.databaseintegration.controllers;


import com.tuoppi.databaseintegration.models.*;
import com.tuoppi.databaseintegration.persistence.EntityDAO;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tuoppi
 * 
 * Exposes RESTful XML based CRUD channel on database to client.
 * Sends and receives XML-serialized entities, maps them to objects and delegates
 * them for DAO to be persisted.
 */
@RestController
@RequestMapping("/database")
public class FetchXmlFromDatabaseController {
    
    private final EntityDAO entityDAO;

    
    @Autowired
    public FetchXmlFromDatabaseController(EntityDAO entityDAO) {
        this.entityDAO = entityDAO;
    }
    
    /**
     *
     * @return
     * 
     * Client connects here to retrieve full XML representation of database
     */
    @RequestMapping(value = "/fetch", method = GET,
            produces = MediaType.APPLICATION_XML_VALUE)
    public FetchReply getAllAlbums() {
        
        List<Album> albums = entityDAO.getAllEntities(Album.class);
        return new FetchReply(albums);
    }
    
    /* Update and insert methods */
    
    // TODO: validation

    /**
     *
     * @param album
     * @return
     * @throws IOException
     * 
     * @RequestBody means that XML is automatically converted to object
     * with available HTTPMessageConverter (JAXB)
     */
        @RequestMapping(value = "/album", method = POST,
            consumes = MediaType.APPLICATION_XML_VALUE)
    public String updateAlbum(@RequestBody Album album) throws IOException {
        
        System.out.println("connection made");
        entityDAO.saveOrUpdate(album);
        return "Album updated";
    }
    
    @RequestMapping(value = "/artist", method = POST,
            consumes = MediaType.APPLICATION_XML_VALUE)
    public String updateArtist(@RequestBody Artist artist) {
        
        entityDAO.saveOrUpdate(artist);
        return "Artist updated";
    }
    
    @RequestMapping(
            value = "/track", method = POST,
            consumes = MediaType.APPLICATION_XML_VALUE)
    public String updateTrack(@RequestBody Track track) {
            
        entityDAO.saveOrUpdate(track);
        return "Track updated";
    }
    
    /* Fetch by id methods */
    
    @RequestMapping(value = "/album/{id}", method = GET)
    public Album getAlbum(@PathVariable int id) {
        
        Optional<Album> album = entityDAO.getEntityById(Album.class, id);
        return album.get();
    }
    
    @RequestMapping(value = "/artist/{id}", method = GET)
    public Artist getArtist(@PathVariable int id) {
        
        Optional<Artist> artist = entityDAO.getEntityById(Artist.class, id);
        return artist.get();
    }
    
    @RequestMapping(value = "/track/{id}", method = GET)
    public Track getTrack(@PathVariable int id) {
        
        Optional<Track> track = entityDAO.getEntityById(Track.class, id);
        return track.get();
    }
    
    /* Delete methods */
    
    @RequestMapping(value = "/album/{id}", method = DELETE)
    public String deleteAlbum(@PathVariable int id) {
        
        Optional<Album> album = entityDAO.getEntityById(Album.class, id);
        if (album.isPresent())
            entityDAO.detete(album.get());
        
        return "Album deleted";
    }
    
    @RequestMapping(value = "/artist/{id}", method = DELETE)
    public String deleteArtist(@PathVariable int id) {
        
        Optional<Artist> artist = entityDAO.getEntityById(Artist.class, id);
        if (artist.isPresent())
            entityDAO.detete(artist.get());
        
        return "Artist deleted";
    }
    
    @RequestMapping(value = "/track/{id}", method = DELETE)
    public String deleteTrack(@PathVariable int id) {
        
        Optional<Track> track = entityDAO.getEntityById(Track.class, id);
        if (track.isPresent())
            entityDAO.detete(track.get());
        
        return "Track deleted";
    }
    
    /**
     *
     * @return
     * 
     * Resets database to it's initial state for presentation purposes
     */
    @RequestMapping(value = "/reset", method = GET)
    public String resetDatabase() {
        
        Artist direstraits = new Artist("Dire Straits");
        Artist beachhouse = new Artist("Beach House");
        
        List<Track> brotherstracks = Arrays.asList(
                
                new Track("So Far Away", 5.12),
                new Track("Money for Nothing", 8.26),
                new Track("Walk of Life", 4.07),
                new Track("Your Latest Trick", 4.46),
                new Track("Why Worry", 5.22),
                new Track("Ride Across the River", 6.58),
                new Track("The Man's Too Strong", 4.40),
                new Track("One World", 3.40),
                new Track("Brothers in Arms", 6.55)
        );
        
        List<Track> loveovergoldtracks = Arrays.asList(
                
                new Track("Telegraph Road", 14.20),
                new Track("Private Investigations", 6.47),
                new Track("Industrial Disease", 5.50),
                new Track("Love over Gold", 6.18),
                new Track("It Never Rains"  , 8.00)
        );
        
        List<Track> devotionTracks = Arrays.asList(
                
                new Track("Wedding Bell", 3.55),
                new Track("You Came to Me", 4.05),
                new Track("Gila", 4.46),
                new Track("Turtle Island", 4.00),
                new Track("Holy Dances", 4.19),
                new Track("All the Years", 3.36),
                new Track("Heart of Chambers", 4.25),
                new Track("Some Things Last a Long Time", 2.32),
                new Track("Astronaut", 5.05),
                new Track("D.A.R.L.I.N.G.", 3.18),
                new Track("Home Again", 4.09)
        );
        
        Album brothers = new Album("Brothers in Arms", Arrays.asList(direstraits), brotherstracks);
        Album loveovergold = new Album("Love over Gold", Arrays.asList(direstraits), loveovergoldtracks);
        Album devotion = new Album("Devotion", Arrays.asList(beachhouse), devotionTracks);
        
        entityDAO.saveOrUpdate(brothers);
        entityDAO.saveOrUpdate(loveovergold);
        entityDAO.saveOrUpdate(devotion);
        
        return "Database initialized";
    }
    
}