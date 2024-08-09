package habsida.spring.boot_security.demo.service;

import habsida.spring.boot_security.demo.model.Role;
import habsida.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    void updateUser(Long id, User updatedUser);

    void removeUserById(Long id);

    List<User> getAllUsers();

    List<Role> getRoles();


}
