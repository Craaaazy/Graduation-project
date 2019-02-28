package cn.ning.ysestate.dto;

import lombok.Data;

@Data
public class HouseDto {

    private String id;

    private String title;

    private String zone;
    private String locate;
    private String detail;
    private String house_pic;
    private boolean isCheck; // false for 未审核, true for 已审核.

    private String rentPrice;
    private String sellPrice;

    private String uploadTime;

    private int click_Num;
    private String comment_Star;
    private String userId;

}
