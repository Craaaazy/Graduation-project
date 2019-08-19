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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        Specification<HouseInfo> specification = new Specification() {
            @Nullable
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Predicate predicate = criteriaBuilder.equal(root.get("isSell"), false);

                return criteriaBuilder.and(predicate);

            }
        };

        Page<HouseInfo> page = houseService.findAll(specification ,pageable);
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
        houseDto.setSellPrice(houseInfo.getSellPrice());
        houseDto.setTitle(houseInfo.getTitle());
        houseDto.setUploadTime(houseInfo.getUploadTime());
        houseDto.setUserId(houseInfo.getUser().getId());
        houseDto.setZone(houseInfo.getZone());
        houseDto.setCheck(houseInfo.isCheck());
        houseDto.setOwner_name(houseInfo.getUser().getUsername());
        houseDto.setOwner_phone(houseInfo.getUser().getPhone_num());
        houseDto.setOwner_email(houseInfo.getUser().getEmail());
        houseDto.setHouse_pic(houseInfo.getPic_front());
        houseDto.setPicture(houseInfo.getHouse_pic());
        modelMap.addAttribute("house", houseDto);

        return "rentals_single";

    }


    @GetMapping(value = "/houselist/search={title}")
    public String getSearch(@PathVariable String title, ModelMap modelMap){
        List<HouseInfo> houses = houseService.findByTitleLike("%"+title+"%");
        modelMap.addAttribute("size", houses.size());
        modelMap.addAttribute("houses", houses);
        return "rentals_index_list";
    }


}
