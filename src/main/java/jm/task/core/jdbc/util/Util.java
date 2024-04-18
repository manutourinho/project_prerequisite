package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private final String url = "jdbc:mysql://localhost:3306/project_prerequisite";
    private final String username = "devuser";
    private final String password = "Uvaroxa18*";

    public Connection getLocalConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(getUrl(), getUsername(), getPassword());

    }

    public String getUrl() { return url; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

}
