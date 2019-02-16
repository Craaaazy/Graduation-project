package cn.ning.ysestate.controller;


import cn.ning.ysestate.dto.SimpleUser;
import cn.ning.ysestate.model.HouseInfo;
import cn.ning.ysestate.model.User;
import cn.ning.ysestate.service.HouseService;
import cn.ning.ysestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    HouseService houseService;


    @GetMapping(value = "")
    public String getAdminPage(){
        return "admin";
    }

    /***
     * 获得user的一些信息
     * @return a list of simpleuser imformation
     */
    @ResponseBody
    @GetMapping(value = "/users")
    public List<SimpleUser> getAllUsers(){
        List<SimpleUser> users = new ArrayList<SimpleUser>();
        List<User> userList = userService.findAll();
        for (int i = 0; i < userList.size() ; i++) {
            User user = userList.get(i);
            SimpleUser su =  new SimpleUser(user.getEmail(), user.getHouses(), user.getRole(), user.getUsername());

            users.add(su);
        }

        return users;
    }

    /***
     * 获得全部houseinfo
     * @return all houseinfo
     */
    @GetMapping(value = "/houses")
    public List<HouseInfo> getAllHouses(){
        return houseService.findAll();
    }

    /***
     * 更改审核状态 set true
     * @param map houseinfo's title from
     * @return checked
     */
    @PutMapping(value = "/house")
    @ResponseBody
    public String putHouse(@RequestBody Map<String, String> map){
        HouseInfo houseInfo = houseService.findByTitle(map.get("title"));
        houseInfo.setCheck(true);
        houseService.save(houseInfo);

        return "审核成功";
    }

    /***
     * 删除houseinfo
     * @param map houseinfo's title from
     * @return deleted
     */
    @DeleteMapping(value = "/house")
    @ResponseBody
    public String deletehouse(@RequestBody HashMap<String, String> map){  //试试hashmap
        houseService.deleteByTitle(map.get("title"));
        return "删除成功";
    }

}
