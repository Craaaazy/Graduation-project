package cn.ning.ysestate.service;

import cn.ning.ysestate.model.Role;

public interface RoleService {
    Role findByName(String rolename);
}
