package com.projectprerequisite.service;

import com.projectprerequisite.model.User;

import java.util.List;

public interface UserService {

    void saveOrUpdateUser(User user);

    void removeUserById(long id);

    User getUserById(long id);

    List<User> getAllUsers();

}
