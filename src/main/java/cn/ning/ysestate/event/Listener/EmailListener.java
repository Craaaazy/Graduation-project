package cn.ning.ysestate.event.Listener;


import cn.ning.ysestate.dto.EmailDto;
import cn.ning.ysestate.event.OnEmailEvent;
import cn.ning.ysestate.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {

    private EmailDto emailDto;

    @Autowired
    EmailService emailService;

    @EventListener
    @Async
    public void HandleEmailEvent(OnEmailEvent emailEvent){
        this.emailDto = emailEvent.getEmailDto();
        emailService.send(emailDto);
    }

}
