package cn.ning.ysestate.event.Listener;

import cn.ning.ysestate.model.Role;
import cn.ning.ysestate.model.User;
import cn.ning.ysestate.service.RoleService;
import cn.ning.ysestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Role role1 = new Role();
        Role role2 = new Role();
        role1.setName("ROLE_USER");
        role2.setName("ROLE_ADMIN");

        roleService.save(role1);
        roleService.save(role2);

        User user = new User();
        user.setUsername("ning123");
        user.setPassword("123");
        user.setActive(true);
        user.setRole(role1);
        user.setEmail("382712256@qq.com");

        userService.save(user);
    }
}
