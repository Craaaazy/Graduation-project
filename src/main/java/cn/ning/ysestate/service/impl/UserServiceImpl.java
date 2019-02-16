package cn.ning.ysestate.service.impl;

import cn.ning.ysestate.model.User;
import cn.ning.ysestate.repository.UserReprository;
import cn.ning.ysestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserReprository userReprository;

    @Override
    public User save(User user) {
        return userReprository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userReprository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userReprository.findByEmail(email);
    }

    @Override
    public User findByVaridateCode(String code) {
        return userReprository.findByVaridateCode(code);
    }

    @Override
    public List<User> findAll() {
        return userReprository.findAll();
    }
}
