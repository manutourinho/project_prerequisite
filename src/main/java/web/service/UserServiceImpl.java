package web.service;

import hiber.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.User;

import javax.transaction.Transactional;
import java.util.List;

//interacts with the DB/hibernate
@Service
@Transactional
public class UserServiceImpl implements UserService {

    UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void saveOrUpdateUser(User user) {
        userDao.saveOrUpdateUser(user);
    }

    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
