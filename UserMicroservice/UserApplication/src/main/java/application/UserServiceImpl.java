package application;

import contracts.UserService;
import jakarta.transaction.Transactional;
import mapper.UserMapper;
import models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repositories.UserDao;


@Service
@Transactional
@ComponentScan(basePackages = {"repositories", "config"})
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void addUser(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(UserMapper.modelToEntity(user));
    }
}
