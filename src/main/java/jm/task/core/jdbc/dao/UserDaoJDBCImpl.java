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

            statement.executeUpdate("create table if not exists users ("
                    + "id INTEGER primary key auto_increment, "
                    + "name varchar(50) not null, "
                    + "last_name varchar(50) not null, "
                    + "age TINYINT not null");
        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Statement statement = util.getLocalConnection()
                .createStatement()) {
            statement.executeUpdate("drop table if exists users");
        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = util.getLocalConnection()
                .prepareStatement("insert into users (name, lastName, age) values (?, ?, ?)")) {
            statement.setString(2, name);
            statement.setString(3, lastName);
            statement.setByte(4, age);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = util.getLocalConnection()
                .prepareStatement("delete from users where id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = util.getLocalConnection()
                .createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users")) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4));
                users.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = util.getLocalConnection()
                .createStatement()) {
            statement.executeUpdate("delete from users");
        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();
        }
    }
}
