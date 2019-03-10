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
import java.util.Optional;

@Controller
@RequestMapping(value = "/trade")
public class TradeControler {

    @Autowired
    HouseService houseService;
    @Autowired
    UserService userService;


    @GetMapping(value = "/{id}")
    public String getTrade(@PathVariable String id, ModelMap modelMap){
        Optional<HouseInfo> houseInfo = houseService.findById(id);
        HouseDto house = new HouseDto();
        house.setTitle(houseInfo.get().getTitle());
        house.setId(houseInfo.get().getId());
        house.setZone(houseInfo.get().getZone());
        house.setId(houseInfo.get().getId());
        house.setSellPrice(houseInfo.get().getSellPrice());
        house.setOwner_name(houseInfo.get().getUser().getUsername());
        house.setOwner_email(houseInfo.get().getUser().getEmail());
        house.setOwner_phone(houseInfo.get().getUser().getPhone_num());
        house.setLocate(houseInfo.get().getLocate());

        modelMap.addAttribute("house", house);
        return "misc_invoice";
    }


    @PostMapping(value = "/{id}")
    @ResponseBody
    public String postTrade(@PathVariable String id, Principal principal){

        try{

            Optional<HouseInfo> houseInfo = houseService.findById(id);
            User buyer = userService.findByUsername(principal.getName());
            User seller = userService.findByUsername(houseInfo.get().getUser().getUsername());

            if(buyer == seller){
                return "无法购买自己上传的商品!";
            }

            if(houseInfo.get().isSell() == true){
                return "无法购买,因为商品已售出";
            }

            Double b_money = Double.valueOf(buyer.getBalance());
            Double s_money = Double.valueOf(seller.getBalance());
            Double h_price = Double.valueOf(houseInfo.get().getSellPrice());

            if(b_money < h_price){
                return "无法购买,因为账户余额不足!";
            }else {
                b_money = b_money - h_price;
                s_money = s_money + h_price;
                houseInfo.get().setSell(true);
                buyer.setBalance(b_money.toString());
                seller.setBalance(s_money.toString());

                seller.setTotal_earn(seller.getTotal_earn() + h_price);
                seller.setHouse_sold(seller.getHouse_sold() + 1);

                houseInfo.get().setSell(true);
                houseService.save(houseInfo.get());
                userService.save(buyer);
                userService.save(seller);

                return "交易成功";
            }


        }catch (Exception e){
            System.out.println(e.getMessage());
            return "ERROR!";
        }


    }


}
