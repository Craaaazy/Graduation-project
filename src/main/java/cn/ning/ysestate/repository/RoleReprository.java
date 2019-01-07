package cn.ning.ysestate.repository;

import cn.ning.ysestate.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleReprository extends JpaRepository<Role, String> {
    Role findByName(String rolename);

}
