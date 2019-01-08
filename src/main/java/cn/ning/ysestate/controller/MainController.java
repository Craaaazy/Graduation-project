package cn.ning.ysestate.controller;

import cn.ning.ysestate.service.HouseService;
import cn.ning.ysestate.service.RoleService;
import cn.ning.ysestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    HouseService houseService;

    @GetMapping("/index")
    public void getIndex(){
        System.out.println(roleService.findByName("1"));
    }


}
