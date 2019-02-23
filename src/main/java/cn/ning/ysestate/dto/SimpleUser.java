package cn.ning.ysestate.dto;

import cn.ning.ysestate.model.HouseInfo;
import cn.ning.ysestate.model.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import java.util.List;

@Data
public class SimpleUser {

    private String email;
    private String username;
    private List<HouseInfo> houses;
    private Role role;

    public SimpleUser(@Email String email, List<HouseInfo> houses, Role role, String username) {
    }

    public SimpleUser(){}
}