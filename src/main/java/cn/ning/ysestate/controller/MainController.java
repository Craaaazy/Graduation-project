package cn.ning.ysestate.controller;

import cn.ning.ysestate.service.HouseService;
import cn.ning.ysestate.service.RoleService;
import cn.ning.ysestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    HouseService houseService;

    @GetMapping("/index")
    public String getIndex(Principal principal){
        System.out.println(principal.getName());
        return "index";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "/login";
    }

    @PostMapping(value = "/login")
    public String logined(){
        return "/index";
    }

}
