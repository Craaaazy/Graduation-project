package cn.ning.ysestate.controller;


import cn.ning.ysestate.model.HouseInfo;
import cn.ning.ysestate.model.User;
import cn.ning.ysestate.service.HouseService;
import cn.ning.ysestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping(value = "/owner")
public class OwnerController {

    @Autowired
    HouseService houseService;
    @Autowired
    UserService userService;


    @GetMapping(value = "/publish")
    public String getPublish(){
        return "publish";
    }


    /***
     * 上传 houseinfo
     * @param map a form from /publish
     * @param principal current user information from spring security
     * @return new houseinfo saved
     */
    @ResponseBody
    @PostMapping(value = "/publish")
    public String postPublish(@RequestBody Map<String, String> map, Principal principal){
                            //当用form提交时用RequestPrarm 用ajax提交时用RequestBody
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


    /***
     * 找到当前用户提交的houseinfo
     * @return a list of houseinfo which current user submit
     */
    @GetMapping(value = "/house")
    @ResponseBody
    public String getHouse(Principal principal){
        return houseService.findByUser(userService.findByUsername(principal.getName()))
    }


    /***
     * 删除houseinfo
     * @param map house title
     * @return delete successed
     */
    @DeleteMapping(value = "/house")
    @ResponseBody
    public String deleteHouse(@RequestBody Map<String, String> map){
        houseService.deleteByTitle(map.get("title"));
        return "删除成功";
    }

}
