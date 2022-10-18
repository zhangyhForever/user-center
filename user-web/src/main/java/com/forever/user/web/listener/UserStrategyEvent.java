package com.forever.user.web.listener;

import com.forever.user.mybatis.entity.UserDO;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class UserStrategyEvent extends ApplicationEvent {

    private List<UserDO> userDOS;

    private UserEventType eventType;

    public UserStrategyEvent(Object source, List<UserDO> userDOS, UserEventType eventType) {
        super(source);
        this.userDOS = userDOS;
        this.eventType = eventType;
    }

    public List<UserDO> getUserDOS() {
        return userDOS;
    }

    public void setUserDOS(List<UserDO> userDOS) {
        this.userDOS = userDOS;
    }

    public enum UserEventType {
        REGISTER,
        UPDATE,
        DELETE
    }

    public UserEventType getEventType() {
        return eventType;
    }

}
