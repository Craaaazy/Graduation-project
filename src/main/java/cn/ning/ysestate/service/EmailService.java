package cn.ning.ysestate.service;

import cn.ning.ysestate.dto.EmailDto;

public interface EmailService {
    public void send(EmailDto emailDto);
}
