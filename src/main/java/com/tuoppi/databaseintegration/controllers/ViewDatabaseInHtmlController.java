package com.tuoppi.databaseintegration.controllers;

import com.tuoppi.databaseintegration.models.Album;
import com.tuoppi.databaseintegration.persistence.EntityDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 *
 * @author Tuoppi
 * 
 * Return HTML table representation of database to client
 */
@Controller
@RequestMapping("/")
public class ViewDatabaseInHtmlController {
    
    private final EntityDAO entityDAO;

    @Autowired
    public ViewDatabaseInHtmlController(EntityDAO entityDAO) {
        this.entityDAO = entityDAO;
    }
    
    @RequestMapping(method = GET)
    public String htmlTableView(Model model) {
        
        List<Album> albums = entityDAO.getAllEntities(Album.class);
        model.addAttribute("albums", albums);
        return "tableview";
    }
    
    @RequestMapping(value = "/thymeleaf", method = GET)
    public String thymeleafView() {
        System.out.println("ok");
        return "thymeleaf";
    }
}