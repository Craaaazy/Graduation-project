package cn.ning.ysestate.controller;


import cn.ning.ysestate.dto.EmailDto;
import cn.ning.ysestate.event.OnEmailEvent;
import cn.ning.ysestate.model.Role;
import cn.ning.ysestate.model.User;
import cn.ning.ysestate.service.RoleService;
import cn.ning.ysestate.service.UserService;
import cn.ning.ysestate.util.ValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(value = "/regis")
public class RegisController {

    @Autowired
    ApplicationEventPublisher eventPublisher;
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;
    @Autowired
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String usermailname;

    private String validPath = "http://localhost:8050/regis/";

    @GetMapping(value = "")
    public String getRegisterUser(){
        return "/regis";
    }

    @PostMapping(value = "")
    public String postRegisterUser(@RequestParam Map<String, String> map){

        String subject = "From YS-estate:请完成邮箱验证";
        String validCode = new ValidateCodeUtil().getValidCode();
        String content = "如果不是本人，请无视该邮件。</br>请在1小时内点击下面的链接来完成验证:" + validPath + validCode;

//        System.out.println(content);
//        String rolename = "ROLE_" + map.get("role").toUpperCase();
//        Role role = roleService.findByName(rolename);

        Role role_user = roleService.findByName("ROLE_USER");

        User user = new User();
        user.setUsername(map.get("username"));
        user.setPassword(map.get("password"));
        user.setEmail(map.get("email"));
        user.setRole(role_user);
        user.setVaridateCode(validCode);

//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(usermailname);
//        message.setText(content);
//        message.setSubject(subject);
//        message.setTo(map.get("email"));
//
//        javaMailSender.send(message);

        EmailDto emailDto = new EmailDto();
        emailDto.setTo(map.get("email"));

        userService.save(user);
        emailDto.setSubject(subject);
        emailDto.setContent(content);
        emailDto.setFrom(usermailname);

        try {
            eventPublisher.publishEvent(new OnEmailEvent(emailDto));
        }catch (Exception  e){
            System.out.println("发送邮件遇到异常");
            e.printStackTrace();
        }

        userService.save(user);

        return "/login";

    }

    @GetMapping(value = "/{validCode}")
    public String validate(@PathVariable String validCode){
        System.out.println("entered");
        User user = userService.findByVaridateCode(validCode);
        user.setActive(true);
        userService.save(user);

        return "/validSuccess";
    }

}
