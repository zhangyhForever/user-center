package com.forever.user.web.listener;

import com.forever.user.mybatis.entity.UserDO;
import org.springframework.context.ApplicationListener;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserStrategyListener implements ApplicationListener<UserStrategyEvent> {

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
        Set<String> emails = userDOS.stream().map(UserDO::getEmail).collect(Collectors.toSet());

    }

    private void doDeleteEvent(UserStrategyEvent event) {
        // ignore
    }

    private void doUpdateEvent(UserStrategyEvent event) {
        // ignore
    }


}
