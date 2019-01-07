package cn.ning.ysestate.repository;

import cn.ning.ysestate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReprository extends JpaRepository<User, String> {
    User save(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    User findByVaridateCode(String code);

}
