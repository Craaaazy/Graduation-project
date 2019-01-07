package cn.ning.ysestate.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class User {
    /**
     *
     */

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotNull
    private String username;
    @NotNull
    private String password;

    private String email;
    private Boolean active;
    private String varidateCode;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


}
