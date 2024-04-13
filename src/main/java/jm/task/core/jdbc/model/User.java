package jm.task.core.jdbc.model;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Table
public class User {
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private Byte age;

    public User() {

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        UserDaoJDBCImpl getAllUsersStringTable = new UserDaoJDBCImpl();
        List<User> userList = getAllUsersStringTable.getAllUsers();

        StringBuilder sb = new StringBuilder();
        for (User user : userList) {
            sb.append("Name: ").append(user.getName())
                    .append("\nLast Name: ").append(user.getLastName())
                    .append("\nAge: ").append(user.getAge());
        }
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }
}
