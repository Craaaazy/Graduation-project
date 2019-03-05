package cn.ning.ysestate.controller;

import cn.ning.ysestate.dto.HouseDto;
import cn.ning.ysestate.dto.SimpleUser;
import cn.ning.ysestate.model.HouseInfo;
import cn.ning.ysestate.service.HouseService;
import cn.ning.ysestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HouseController {

    @Autowired
    HouseService houseService;
    @Autowired
    UserService userService;


    @GetMapping("/houselist")
    public ModelAndView houselist(@RequestParam(value = "start", defaultValue = "0") Integer start,
                                     @RequestParam(value = "limit", defaultValue = "6") Integer limit) {
        start = start < 0 ? 0 : start;
        Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "uploadTime").descending();
        Pageable pageable = PageRequest.of(start, limit, sort);
        Page<HouseInfo> page = houseService.findAll(pageable);
//        System.out.println(page.getNumber());
//        System.out.println(page.getNumberOfElements());
//        System.out.println(page.getSize());
//        System.out.println(page.getTotalElements());
//        System.out.println(page.getTotalPages());
//        System.out.println(page.isFirst());
//        System.out.println(page.isLast());

        ModelAndView mav = new ModelAndView("rentals_index_grid");
        mav.addObject("page", page);

        return mav;

    }

    @GetMapping(value = "/house_detail/{house_id}")
    public String getHouseDetail(@PathVariable String house_id, ModelMap modelMap){

        HouseInfo houseInfo = houseService.findById(house_id).get();
        houseInfo.setClick_Num(houseInfo.getClick_Num()+1);
        houseService.save(houseInfo);

        HouseDto houseDto = new HouseDto();
        houseDto.setClick_Num(houseInfo.getClick_Num());
        houseDto.setComment_Star(houseInfo.getComment_Star());
        houseDto.setDetail(houseInfo.getDetail());
        houseDto.setId(houseInfo.getId());
        houseDto.setLocate(houseInfo.getLocate());
        houseDto.setRentPrice(houseInfo.getRentPrice());
        houseDto.setSellPrice(houseInfo.getSellPrice());
        houseDto.setTitle(houseInfo.getTitle());
        houseDto.setUploadTime(houseInfo.getUploadTime());
        houseDto.setUserId(houseInfo.getUser().getId());
        houseDto.setHouse_pic(houseInfo.getHouse_pic());
        houseDto.setZone(houseInfo.getZone());
        houseDto.setCheck(houseInfo.isCheck());
        houseDto.setOwner_name(houseInfo.getUser().getUsername());
        houseDto.setOwner_phone(houseInfo.getUser().getPhone_num());
        houseDto.setOwner_email(houseInfo.getUser().getEmail());

        modelMap.addAttribute("house", houseDto);

        return "rentals_single";

    }




}
