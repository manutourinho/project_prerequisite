package habsida.spring.boot_security.demo.service;

import habsida.spring.boot_security.demo.model.Role;
import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.repository.RoleRepository;
import habsida.spring.boot_security.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    @Override
    public void updateUser(Long id, User existingUser) {
        User updatedUser = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found :("));

        // update fields!!
        updatedUser.setFirstName(existingUser.getFirstName());
        updatedUser.setLastName(existingUser.getLastName());
        updatedUser.setAge(existingUser.getAge());
        updatedUser.setEmail(existingUser.getEmail());
        updatedUser.setPassword(bCryptPasswordEncoder.encode(existingUser.getPassword()));
        updatedUser.setRoles(existingUser.getRoles());

        // saving the updated user
        userRepository.save(updatedUser);
    }

    @Override
    public void removeUserById(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(email);

        if (user == null) {
            throw new UsernameNotFoundException("Email " + email + " not found :(");

        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities());

    }
}
