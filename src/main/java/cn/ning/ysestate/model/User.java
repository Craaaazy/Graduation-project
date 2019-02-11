package cn.ning.ysestate.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;

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

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<HouseInfo> houses;

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
