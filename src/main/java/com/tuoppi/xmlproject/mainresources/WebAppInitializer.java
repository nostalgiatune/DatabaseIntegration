package com.tuoppi.xmlproject.mainresources;


import com.tuoppi.databaseintegration.configuration.RootConfig;
import com.tuoppi.databaseintegration.configuration.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author Tuoppi
 * 
 * Bootstraps Spring when Servlet container is started
 */
public class WebAppInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        System.out.println("ROOT CONFIG OK");
        return new Class<?>[] { RootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        System.out.println("WEB CONFIG OK");
        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        System.out.println("SERVLET MAPPINGS OK");
        return new String[] { "/" };
    }
    
}