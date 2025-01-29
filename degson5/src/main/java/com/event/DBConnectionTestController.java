package com.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class DBConnectionTestController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/test-db-connection")
    public String testDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return "Connected to the database successfully! URL: " + connection.getMetaData().getURL();
        } catch (Exception e) {
            return "Failed to connect to the database. Error: " + e.getMessage();
        }
    }
}
