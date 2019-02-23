package cn.ning.ysestate.controller;

import cn.ning.ysestate.service.HouseService;
import cn.ning.ysestate.service.RoleService;
import cn.ning.ysestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MainController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    HouseService houseService;

    @GetMapping("/index")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "/auth_login";
    }

    @PostMapping(value = "/login")
    public String logined(){
        return "/index";
    }


}
