package cn.ning.ysestate.controller;


import cn.ning.ysestate.dto.BillsDto;
import cn.ning.ysestate.dto.HouseDto;
import cn.ning.ysestate.dto.SimpleUser;
import cn.ning.ysestate.model.Billings;
import cn.ning.ysestate.model.HouseInfo;
import cn.ning.ysestate.model.User;
import cn.ning.ysestate.service.BillsService;
import cn.ning.ysestate.service.HouseService;
import cn.ning.ysestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    HouseService houseService;
    @Autowired
    BillsService billsService;


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

    @GetMapping(value = "/houses")
    public String getMyHouses(ModelMap modelMap){
        List<HouseInfo> houseInfo = houseService.findAll();
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
            house.setClick_Num(houseInfo.get(i).getClick_Num());

            houses.add(house);
        }

        modelMap.addAttribute("houses", houses);
        return "tables_admin";
    }


    @PutMapping(value = "/house/{id}")
    @ResponseBody
    public String putHouse(@PathVariable String id){
        HouseInfo houseInfo = houseService.findById(id).get();
        houseInfo.setCheck(true);
        houseService.save(houseInfo);

        return "审核成功";
    }

    @DeleteMapping(value = "/house/{id}")
    @ResponseBody
    public String deletehouse(@PathVariable String id){
        houseService.deleteById(id);
        return "删除成功";
    }

    @GetMapping(value = "/billings")
    public String getBillings(ModelMap modelMap){
        List<Billings> bills = billsService.findAll();
        List<BillsDto> billsDtos = new ArrayList<>();
        BillsDto b;

        for(int i=0; i<bills.size(); i++){
            b = new BillsDto();
            b.setId(bills.get(i).getId());
            b.setTitle(bills.get(i).getHouse().getTitle());
            b.setSeller(bills.get(i).getHouse().getUser().getUsername());
            b.setSellPrice(bills.get(i).getHouse().getSellPrice());
            b.setTime(bills.get(i).getTime());
            b.setBuyer(bills.get(i).getBuyer().getUsername());

            billsDtos.add(b);
        }

        modelMap.addAttribute("bills", billsDtos);
        return "tables_billings_admin";
    }

}
