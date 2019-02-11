package cn.ning.ysestate.controller;


import cn.ning.ysestate.model.HouseInfo;
import cn.ning.ysestate.model.User;
import cn.ning.ysestate.service.HouseService;
import cn.ning.ysestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.reflect.annotation.ExceptionProxy;
import sun.util.calendar.BaseCalendar;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class OwnerController {

    @Autowired
    HouseService houseService;
    @Autowired
    UserService userService;


    @GetMapping(value = "/publish")
    public String getPublish(){
        return "publish";
    }

    @ResponseBody
    @PostMapping(value = "/publish")
    public String postPublish(@RequestParam Map<String, String> map, Principal principal){

        try {

            HouseInfo houseInfo = new HouseInfo();
            houseInfo.setCheck(false);
            houseInfo.setDetail(map.get("detail"));
            houseInfo.setLocate(map.get("locate"));
            houseInfo.setTitle(map.get("title"));
            houseInfo.setRentPrice(map.get("rentPrice"));
            houseInfo.setSellPrice(map.get("sellPrice"));
            houseInfo.setZone(map.get("zone"));

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            houseInfo.setUploadTime(df.format(new Date()));

            User user = userService.findByUsername(principal.getName());

            houseInfo.setUser(user);

            houseInfo.setHouse_pic("xxxxxx");  //上传图片  先搁置

            houseService.save(houseInfo);
            return "上传成功，等待审核...";
        }catch (Exception e){
            e.printStackTrace();
            return "an error occured...";
        }

    }



}
