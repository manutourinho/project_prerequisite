package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private final String url = "jdbc:mysql://localhost:3306/task_1_2";
    private final String username = "devuser";
    private final String password = "Uvaroxa18*";

    public void getLocalConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from User");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2)
                    + " "
                    + resultSet.getString(3));
        }

        connection.close();

    }

    public String getUrl() { return url; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

}
