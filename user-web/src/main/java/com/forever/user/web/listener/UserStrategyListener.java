package com.forever.user.web.listener;

import com.forever.user.mybatis.entity.UserDO;
import com.forever.user.web.config.MailSendConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserStrategyListener {

    private Logger logger = LoggerFactory.getLogger(UserStrategyListener.class);

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private MailSendConfig mailSendConfig;

    @EventListener
    public void onApplicationEvent(UserStrategyEvent event) {
        UserStrategyEvent.UserEventType eventType = event.getEventType();
        switch (eventType) {
            case REGISTER:
                try {
                    doRegisterEvent(event);
                } catch (Exception e) {
                    logger.error("用户注册邮件发送失败！");
                    // 补偿重发
                }
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
        if (!mailSendConfig.isEnable()) {
            return;
        }
        String emailStrList = userDOS.stream().map(UserDO::getEmail).collect(Collectors.joining(","));
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailSendConfig.getFrom());
        simpleMailMessage.setSubject(mailSendConfig.getTitle());
        simpleMailMessage.setText(mailSendConfig.getContent());
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
