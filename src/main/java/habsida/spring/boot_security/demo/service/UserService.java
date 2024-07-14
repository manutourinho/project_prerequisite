package habsida.spring.boot_security.demo.service;

import habsida.spring.boot_security.demo.model.Role;
import habsida.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    User loginUser(String email, String password);
    void saveUser(User user);

    void updateUser(Long id, User updatedUser);

    void removeUserById(Long id);

    List<User> getAllUsers();

    List<Role> getRoles();

//    String createAcc(Long idRole, Long idUser);

}
