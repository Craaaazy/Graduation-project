package cn.ning.ysestate.event.Listener;

import cn.ning.ysestate.model.HouseInfo;
import cn.ning.ysestate.model.Role;
import cn.ning.ysestate.model.User;
import cn.ning.ysestate.service.HouseService;
import cn.ning.ysestate.service.RoleService;
import cn.ning.ysestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;
    @Autowired
    HouseService houseService;

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
        user.setPhone_num("12345679");

        userService.save(user);

        HouseInfo houseInfo;
        for (int i = 0; i < 10; i++) {
            houseInfo = new HouseInfo();
            houseInfo.setCheck(false);
            houseInfo.setZone("西塞山区");
            houseInfo.setSellPrice("100");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            houseInfo.setUploadTime(df.format(new Date()));
            houseInfo.setUser(userService.findByUsername("ning123"));
            houseInfo.setTitle("NO." + i +" testhouse" + i);
            houseInfo.setHouse_pic("xxx");
            houseInfo.setClick_Num((int)(Math.random()*100));

            houseService.save(houseInfo);
        }

    }
}
