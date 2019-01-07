package cn.ning.ysestate.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {
    /**
     * role table
     * 普通用户，管理员
     */

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;

}
