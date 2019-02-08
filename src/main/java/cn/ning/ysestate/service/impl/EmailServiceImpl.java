package cn.ning.ysestate.service.impl;

import cn.ning.ysestate.dto.EmailDto;
import cn.ning.ysestate.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void send(EmailDto emailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDto.getTo());
        message.setSubject(emailDto.getSubject());
        message.setFrom(emailDto.getFrom());
        message.setText(emailDto.getContent());

        javaMailSender.send(message);
    }
}
