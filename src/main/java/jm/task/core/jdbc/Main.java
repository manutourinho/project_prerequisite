package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        UserDaoJDBCImpl tableUserDaoJDBC = new UserDaoJDBCImpl();

        tableUserDaoJDBC.createUsersTable();

        User userOne = new User("Manuela", "Tourinho", (byte) 26);
        tableUserDaoJDBC.saveUser(userOne.getName(), userOne.getLastName(), userOne.getAge());
        System.out.println("User with name - " + userOne.getName() + " added to the database");

        User userTwo = new User("Sandra", "Tourinho", (byte) 5);
        tableUserDaoJDBC.saveUser(userTwo.getName(), userTwo.getLastName(), userTwo.getAge());
        System.out.println("User with name - " + userTwo.getName() + " added to the database");

        User userThree = new User("José", "Junior", (byte) 2);
        tableUserDaoJDBC.saveUser(userThree.getName(), userThree.getLastName(), userThree.getAge());
        System.out.println("User with name - " + userThree.getName() + " added to the database");

        User userFour = new User("Maria Luiza", "Tourinho", (byte) 2);
        tableUserDaoJDBC.saveUser(userFour.getName(), userFour.getLastName(), userFour.getAge());
        System.out.println("User with name - " + userFour.getName() + " added to the database");

        tableUserDaoJDBC.getAllUsers();
        System.out.println(userOne.toString());
        tableUserDaoJDBC.cleanUsersTable();
        tableUserDaoJDBC.dropUsersTable();
    }
}

