package cn.ning.ysestate.service;

import cn.ning.ysestate.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    User findByVaridateCode(String code);
    List<User> findAll();
    Optional<User> findById(String id);

}
