package com.projectprerequisite.dao;

import com.projectprerequisite.model.User;

import java.util.List;

public interface UserDao {

    void saveOrUpdateUser(User user);

    void removeUserById(long id);

    User getUserById(long id);

    List<User> getAllUsers();

}
