package cn.ning.ysestate.controller;


import cn.ning.ysestate.dto.HouseDto;
import cn.ning.ysestate.model.HouseInfo;
import cn.ning.ysestate.model.User;
import cn.ning.ysestate.service.HouseService;
import cn.ning.ysestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        return "forms_regular";
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
        HouseInfo houseInfo = new HouseInfo();

        try {

            houseInfo.setCheck(false);
            houseInfo.setDetail(map.get("detail"));
            houseInfo.setLocate(map.get("locate"));
            houseInfo.setTitle(map.get("title"));
            houseInfo.setRentPrice(map.get("rent"));
            houseInfo.setSellPrice(map.get("sell"));
            houseInfo.setZone(map.get("zone"));

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            houseInfo.setUploadTime(df.format(new Date()));
            User user = userService.findByUsername(principal.getName());
            if(user!=null){
                houseInfo.setUser(user);
                houseService.save(houseInfo);

                return "SUCCESS";
            }else {
                return "未登录";
            }




        }catch (Exception e){
            e.printStackTrace();
            return "ERROR";
        }

    }

    @PostMapping("/test")
    @ResponseBody
    public String test(){
        return "dd";
    }


    /***
     * 找到当前用户提交的houseinfo
     * @return a list of houseinfo which current user submit
     */
    @GetMapping(value = "/house")
    @ResponseBody
    public List<HouseInfo> getHouse(Principal principal){
        return houseService.findByUser(userService.findByUsername(principal.getName()));
    }


    /***
     * 删除houseinfo
     * @param map house title
     * @return delete successed
     */
    @DeleteMapping(value = "/house")
    @ResponseBody
    public String deleteHouse(@RequestBody Map<String, String> map){
        houseService.deleteById(map.get("id"));
        return "删除成功";
    }


    @GetMapping(value = "/myHouse")
    public String getMyHouses(Principal principal, ModelMap modelMap){
        List<HouseInfo> houseInfo = houseService.findByUser(userService.findByUsername(principal.getName()));
        List<HouseDto> houses = new ArrayList<>();
        HouseDto house;

        for (int i = 0; i < houseInfo.size(); i++) {
            house = new HouseDto();
            house.setZone(houseInfo.get(i).getZone());
            house.setUploadTime(houseInfo.get(i).getUploadTime());
            house.setTitle(houseInfo.get(i).getTitle());
            house.setCheck(houseInfo.get(i).isCheck());
            house.setId(houseInfo.get(i).getId());
            house.setSellPrice(houseInfo.get(i).getSellPrice());
            house.setRentPrice(houseInfo.get(i).getRentPrice());
            house.setClick_Num(houseInfo.get(i).getClick_Num());

            houses.add(house);
        }

        modelMap.addAttribute("houses", houses);
        return "tables_regular";
    }


    @DeleteMapping(value = "/myHouse/{id}")
    @ResponseBody
    public String deleteHouse(@PathVariable String id){
        houseService.deleteById(id);
        return "delete success";
    }

}
