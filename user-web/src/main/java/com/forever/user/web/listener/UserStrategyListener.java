package com.forever.user.web.listener;

import com.forever.user.mybatis.entity.UserDO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

public class UserStrategyListener implements ApplicationListener<UserStrategyEvent> {

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${application.mail.enable:false}")
    private boolean mailEnable;

    @Value("${application.mail.from}")
    private String mailFrom;

    @Value("${application.mail.title}")
    private String title;

    @Value("${application.mail.content}")
    private String content;

    @Override
    public void onApplicationEvent(UserStrategyEvent event) {
        UserStrategyEvent.UserEventType eventType = event.getEventType();
        switch (eventType) {
            case REGISTER:
                doRegisterEvent(event);
                break;
            case UPDATE:
                doUpdateEvent(event);
                break;
            case DELETE:
                doDeleteEvent(event);
                break;
            default:
                throw new RuntimeException("不支持的事件类型");
        }
    }

    /**
     * 注册事件发送邮件
     * @param event
     */
    private void doRegisterEvent(UserStrategyEvent event) {
        List<UserDO> userDOS = event.getUserDOS();
        if (CollectionUtils.isEmpty(userDOS)) {
            return;
        }
        if (!mailEnable) {
            return;
        }
        String emailStrList = userDOS.stream().map(UserDO::getEmail).collect(Collectors.joining(","));
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailFrom);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(content);
        simpleMailMessage.setTo(emailStrList);
        javaMailSender.send(simpleMailMessage);
    }

    private void doDeleteEvent(UserStrategyEvent event) {
        // ignore
    }

    private void doUpdateEvent(UserStrategyEvent event) {
        // ignore
    }


}
