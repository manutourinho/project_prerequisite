package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class Util {
    private final String url = "jdbc:mysql://localhost:3306/project_prerequisite";
    private final String username = "devuser";
    private final String password = "Uvaroxa18*";

    public Connection getLocalConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(getUrl(), getUsername(), getPassword());

    }

    public SessionFactory getHibernateConnection() {

        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", getUrl());
        configuration.setProperty("hibernate.connection.username", getUsername());
        configuration.setProperty("hibernate.connection.password", getPassword());
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.addAnnotatedClass(User.class);

        return configuration.buildSessionFactory();

    }

    private String getUrl() { return url; }

    private String getUsername() { return username; }

    private String getPassword() { return password; }
}
