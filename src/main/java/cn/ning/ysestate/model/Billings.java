package cn.ning.ysestate.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
public class Billings {

    @Id
    private String id;

    @ManyToOne
    private User buyer;

    @OneToOne
    private HouseInfo house;

    private String time;
}
