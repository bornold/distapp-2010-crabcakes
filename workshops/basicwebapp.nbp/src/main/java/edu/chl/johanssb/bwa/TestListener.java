/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.johanssb.bwa;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 * @author flipmo
 */
@WebListener()
public class TestListener implements ServletContextListener, HttpSessionListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized CALLED");
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed CALLED");
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("sessionCreated CALLED");
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("sessionDestroyed CALLED");
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
