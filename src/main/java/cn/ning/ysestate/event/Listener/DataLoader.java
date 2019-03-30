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

        User user1 = new User();
        user1.setUsername("ning123");
        user1.setPassword("123");
        user1.setActive(true);
        user1.setRole(role2);
        user1.setEmail("382712256@qq.com");
        user1.setPhone_num("12345679");
        user1.setBalance("5000");
        user1.setHouse_sold("2");
        user1.setTotal_earn("10000");
        user1.setHead_icon("head1.jpg");

        userService.save(user1);

        User user2 = new User();
        user2.setUsername("testuser");
        user2.setPassword("123");
        user2.setActive(true);
        user2.setRole(role1);
        user2.setEmail("111@qq.com");
        user2.setPhone_num("1930485763");
        user2.setBalance("100000");
        user2.setHead_icon("head2.jpg");

        userService.save(user2);

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
            houseInfo.setHouse_pic("test.PNG,test.PNG,test.PNG,");

            int pic = (int) (Math.random()*3);
            houseInfo.setPic_front("property" + pic + ".jpg");

            houseInfo.setClick_Num((int)(Math.random()*100));
            houseInfo.setComment_Star(String.valueOf((int)(Math.random()*5)));

            houseService.save(houseInfo);
        }

    }
}
