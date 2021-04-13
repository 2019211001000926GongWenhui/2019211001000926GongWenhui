package com.GongWenhui.week6.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.sql.Connection;
import java.sql.DriverManager;

@WebListener()
public class JDBCServletContextListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {
    public JDBCServletContextListener() {
    }
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext c = sce.getServletContext();
        String driver = c.getInitParameter("driver");
        String url = c.getInitParameter("url");
        String username = c.getInitParameter("username");
        String password = c.getInitParameter("password");
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url,username,password);
            c.setAttribute("con", con);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
    public void sessionCreated(HttpSessionEvent se) {
    }

    public void sessionDestroyed(HttpSessionEvent se) {
    }
    public void attributeAdded(HttpSessionBindingEvent sbe) {
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
    }
}
