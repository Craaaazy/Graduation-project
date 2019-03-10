package cn.ning.ysestate.controller;


import cn.ning.ysestate.dto.HouseDto;
import cn.ning.ysestate.model.HouseInfo;
import cn.ning.ysestate.model.User;
import cn.ning.ysestate.service.HouseService;
import cn.ning.ysestate.service.UserService;
import cn.ning.ysestate.util.ValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping(value = "/owner")
public class OwnerController {

    @Autowired
    HouseService houseService;
    @Autowired
    UserService userService;
    @Value("${file.uploadPath}")
    String fileUploadPath;

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
            houseInfo.setSellPrice(map.get("sell"));
            houseInfo.setZone(map.get("zone"));

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            houseInfo.setUploadTime(df.format(new Date()));
            User user = userService.findByUsername(principal.getName());
            houseInfo.setUser(user);
            houseInfo.setSell(false);
            houseService.save(houseInfo);

            return "SUCCESS";

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

    @GetMapping(value = "/uploadpic/{id}")
    public String getUploadPage(@PathVariable String id, ModelMap modelMap){
        modelMap.addAttribute("house_id", id);
        return "form_upload";
    }



    @PostMapping(value = "/fileupload/{id}")
    @ResponseBody
    public String upfile(@PathVariable String id, HttpServletRequest request){

        List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        StringBuffer saveFileName = new StringBuffer();

        Optional<HouseInfo> houseInfo = houseService.findById(id);

        for (int i =0; i< files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    String fileName = UUID.randomUUID().toString() +  file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                    saveFileName.append(fileName+ ",");

                    byte[] bytes = file.getBytes();
                    stream =
                            new BufferedOutputStream(new FileOutputStream(new File(fileUploadPath + fileName)));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream =  null;
                    return "You failed to upload " + i + " =>" + e.getMessage();
                }
            } else {
                return "You failed to upload " + i + " becausethe file was empty.";
            }
        }

        houseInfo.get().setHouse_pic(saveFileName.toString());
        houseInfo.get().setPic_front(saveFileName.toString().split(",")[0]);
        houseService.save(houseInfo.get());

        return "upload successful";

    }

}
