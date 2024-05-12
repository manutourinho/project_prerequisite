package hiber.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    void saveOrUpdateUser(User user);

    void removeUserById(long id);

    User getUserById(long id);

    List<User> getAllUsers();

}
