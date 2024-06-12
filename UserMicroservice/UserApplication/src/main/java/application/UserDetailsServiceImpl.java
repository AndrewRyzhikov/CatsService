package application;


import config.security.UserDetailsImpl;
import entities.UserEntity;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repositories.UserDao;

import java.util.Optional;

@Service
@ComponentScan(basePackages = "userDao")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDao userDao;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userDao.findByName(username);
        System.out.println(userEntity.get().getName());
        return userEntity.map(entity -> new UserDetailsImpl(UserMapper.entityToModel(entity)))
                .orElseThrow(() ->new UsernameNotFoundException(String.format("Not Found user by %s", username)));
    }
}
