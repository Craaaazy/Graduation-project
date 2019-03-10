package cn.ning.ysestate.controller;

import cn.ning.ysestate.dto.SimpleUser;
import cn.ning.ysestate.model.User;
import cn.ning.ysestate.service.HouseService;
import cn.ning.ysestate.service.RoleService;
import cn.ning.ysestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;


@Controller
public class MainController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    HouseService houseService;

//    @GetMapping("/product_index")
//    public String getProductIndex(){
//        return "rentals_index_grid";
//    }

    @GetMapping("/index")
    public String getIndex(){
        return "/index";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "/auth_login";
    }

    @PostMapping("/index")
    public String postLogin(){
        return "/index" ;
    }

    @GetMapping(value = "/forms_regular")
    public String regular(){
        return "/forms_regular";
    }

    @GetMapping(value = "/test")
    public String getWait(){
        return "/wait";
    }

}
