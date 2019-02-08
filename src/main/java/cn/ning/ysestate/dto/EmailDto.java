package cn.ning.ysestate.dto;

import lombok.Data;

@Data
public class EmailDto {
    private String from;
    private String to;
    private String subject;
    private String content;
    private String emailId;

}
