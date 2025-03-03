package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util;

    public UserDaoJDBCImpl() {
        util = new Util();
    }

    public void createUsersTable() {
        try (Statement statement = util.getLocalConnection().createStatement()) {
            statement.executeUpdate("create table if not exists USERS ("
                    + "ID int not null auto_increment, "
                    + "NAME varchar(50) not null, "
                    + "LASTNAME varchar(50) not null, "
                    + "AGE TINYINT not null," +
                    "primary key (ID))");

        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();

        }
    }

    public void dropUsersTable() {
        try (Statement statement = util.getLocalConnection()
                .createStatement()) {
            statement.executeUpdate("drop table if exists USERS");

        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = util.getLocalConnection()
                .prepareStatement("insert into USERS (NAME, LASTNAME, AGE) values (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = util.getLocalConnection()
                .prepareStatement("delete from USERS where ID = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();

        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try (Statement statement = util.getLocalConnection()
                .createStatement();
             ResultSet resultSet = statement.executeQuery("select * from USERS")) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("NAME"),
                        resultSet.getString("LASTNAME"),
                        resultSet.getByte("AGE"));
                usersList.add(user);

            }

        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();

        }

        return usersList;
    }

    public void cleanUsersTable() {
        try (Statement statement = util.getLocalConnection()
                .createStatement()) {
            statement.executeUpdate("delete from USERS");

        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();

        }
    }
}
