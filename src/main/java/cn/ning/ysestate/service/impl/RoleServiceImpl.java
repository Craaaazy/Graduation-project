package cn.ning.ysestate.service.impl;

import cn.ning.ysestate.model.Role;
import cn.ning.ysestate.repository.RoleReprository;
import cn.ning.ysestate.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleReprository roleReprository;

    @Override
    public Role findByName(String rolename) {
        return roleReprository.findByName(rolename);
    }
}
