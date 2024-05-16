package com.projectprerequisite.service;

import com.projectprerequisite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projectprerequisite.model.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveOrUpdateUser(User user) {
        userRepository.save(user);

    }

    @Override
    public void removeUserById(long id) {
        userRepository.deleteById(id);

    }

    @Override
    public User getUserById(long id) {
        User user = null;
        if (Objects.nonNull(id)) {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                user = userOptional.get();
            } else {
                throw new RuntimeException("user not found with the id " + id + " :(");
            }
        }
        return user;

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
