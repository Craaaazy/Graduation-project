package cn.ning.ysestate.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class HouseInfo {
    /**
     * 房屋信息
     * 名称，地址，sell价格or rent价格，图片，是否审核，详细，rent or sell，上传时间
     */

    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Max(16)
    @NotNull
    private String title;
    @NotNull
    private String locate;
    @NotNull
    private String house_pic;
    @NotNull
    private boolean isCheck;
    @NotNull
    private String rentOrSell;
    private String rentPrice;
    private String sellPrice;

    private String uploadTime;

    @ManyToOne()
    @JoinColumn(name = "owner")
    private User user;

    private String detail;

}
