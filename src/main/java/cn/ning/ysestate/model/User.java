package cn.ning.ysestate.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class User {
    /**
     * 用户表
     * 用户名，密码，email，验证码，role，房产信息
     */

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(unique = true)
    private String username;
    @NotNull
    private String password;
    @Email
    private String email;
    private Boolean active;
    private String varidateCode;
    private String Phone_num;

    private String balance;
    private String house_sold = "0";
    private String total_earn = "0";

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    private String head_icon;
//    private String un; //现在不写  以后时间有多把用户名显示成这个

    @ManyToMany
    private List<User> friends;

    @OneToMany(mappedBy = "user")
    private List<HouseInfo> houses;

    @OneToMany(mappedBy = "buyer")
    private List<Billings> billings;

    public User(String username, @NotNull String password, @Email String email, Boolean active, String varidateCode, Role role, List<HouseInfo> houses) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = active;
        this.varidateCode = varidateCode;
        this.role = role;
        this.houses = houses;
    }

    public User() {}
}
