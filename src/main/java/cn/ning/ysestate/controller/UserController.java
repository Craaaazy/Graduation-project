package cn.ning.ysestate.controller;

import cn.ning.ysestate.dto.SimpleUser;
import cn.ning.ysestate.model.User;
import cn.ning.ysestate.service.HouseService;
import cn.ning.ysestate.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    HouseService houseService;
    @Value("${file.uploadPath}")
    String fileUploadPath;

    /*
    @GetMapping(value = "/friend")
    @ResponseBody
    public List<SimpleUser> getFriends(Principal principal){
        User user = userService.findByUsername(principal.getName());
        List<String> list = user.getFriends_id();
        User each_user;
        List<SimpleUser> su = null;
        SimpleUser simpleUser;

        for (int i = 0; i < list.size(); i++) {
            simpleUser = new SimpleUser();
            each_user = userService.findById(list.get(i)).get();
            simpleUser.setHead_icon(each_user.getHead_icon());
            simpleUser.setUsername(each_user.getUsername());
            simpleUser.setEmail(each_user.getEmail());

            su.add(simpleUser);
        }

        return su;
    }


    @GetMapping(value = "/friend")
    @ResponseBody
    public String postFriend(@RequestParam Map<String ,String> map, Principal principal){
        User user = userService.findByUsername(principal.getName());

        for (int i = 0; i < user.getFriends_id().size(); i++) {
            if (user.getFriends_id().get(i) == map.get("id")){
                return "此用户已经是您的好友";
            }
        }

        List<String> list = user.getFriends_id();
        list.add(map.get("id"));
        user.setFriends_id(list);

        userService.save(user);

        User target_user = userService.findById(map.get("id")).get();
        list = target_user.getFriends_id();
        list.add(user.getId());
        target_user.setFriends_id(list);

        userService.save(target_user);

        return "添加成功";

    }
*/

    @GetMapping(value = "")
    public String getUser(){
        return "users_profile";
    }


    @GetMapping(value = "/info")
    @ResponseBody
    public SimpleUser getUserInfo(Principal principal, ModelMap modelMap){

        SimpleUser simpleUser = new SimpleUser();
        User user = userService.findByUsername(principal.getName());

        simpleUser.setHead_icon(user.getHead_icon());
        simpleUser.setTotal_earn(user.getTotal_earn());
        simpleUser.setHouse_sold(user.getHouse_sold());
        simpleUser.setUsername(user.getUsername());
        simpleUser.setRole(user.getRole().getName());
        simpleUser.setBalance(user.getBalance());
        simpleUser.setProject_num(String.valueOf(user.getHouses().size()));

        modelMap.addAttribute("user", user);
        return simpleUser;
    }

    @GetMapping(value = "/head")
    public String getHeadPage(){
        return "/form_upload_pic";
    }

    @PostMapping(value = "/head")
    @ResponseBody
    public String postHead(@RequestParam("file") MultipartFile file, Principal principal){

        if (file.isEmpty()){
            return "文件为空";
        }

        User user = userService.findByUsername(principal.getName());
        String fileName = UUID.randomUUID().toString() +  file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(fileUploadPath + fileName)));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        user.setHead_icon(fileName);
        userService.save(user);

        return "头像上传成功";

    }



}
