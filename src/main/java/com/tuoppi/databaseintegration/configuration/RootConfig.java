package com.tuoppi.databaseintegration.configuration;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Tuoppi
 * 
 * Bean definitions and Spring configuration of Root WebApplicationContext
 */
@Configuration
@ComponentScan(basePackages = {"com.tuoppi.databaseintegration.persistence",
    "com.tuoppi.databaseintegration.utility"})
public class RootConfig {
    
    // Let autowiring do da work
}