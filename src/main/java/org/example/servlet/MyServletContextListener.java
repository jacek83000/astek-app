package org.example.servlet;

import jakarta.servlet.ServletContextEvent;
import org.example.model.Receipt;

public class MyServletContextListener implements jakarta.servlet.ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("Initializing receipts...");
        new Receipt("taxi", 20.25, 2);
        new Receipt("train", 15.50, 3);
        new Receipt("plane ticket", 87.99, 1);
        new Receipt("restaurant", 59.99, 1);
    }
}
