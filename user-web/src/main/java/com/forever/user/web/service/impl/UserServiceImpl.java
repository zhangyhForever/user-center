package com.forever.user.web.service.impl;

import com.forever.user.mybatis.dao.UserMapper;
import com.forever.user.mybatis.entity.UserDO;
import com.forever.user.web.listener.UserStrategyEvent;
import com.forever.user.web.vo.req.UserQuery;
import com.forever.user.web.vo.req.UserReq;
import com.forever.user.web.service.UserService;
import com.forever.user.web.vo.resp.UserResp;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    ApplicationContext applicationContext;

    @Override
    public void insert(Collection<UserReq> userReqs) {
        List<UserDO> userDOs = new ArrayList<>();
        userReqs.forEach(x -> userDOs.add(x.toDO()));
        userMapper.insert(userDOs);
        // 发送用户注册成功事件
        applicationContext.publishEvent(new UserStrategyEvent(this, userDOs, UserStrategyEvent.UserEventType.REGISTER));
    }

    @Override
    public int update(UserReq userReqs) {
        UserDO userDO = userReqs.toDO();
        return userMapper.update(userDO);
    }

    @Override
    public List<UserResp> select(UserQuery userQuery) {
        UserDO userDO = userQuery.toDO();
        Integer page = userQuery.getPage();
        Integer pageSize = userQuery.getPageSize();
        int startIndex = (page - 1) * pageSize;
        List<UserDO> userDOS = userMapper.select(userDO, startIndex, pageSize);
        List<UserResp> userResps = Lists.newArrayList();
        userDOS.forEach(x -> userResps.add(UserResp.of(x)));
        return userResps;
    }

    @Override
    public int delete(Collection<Long> userIds) {
        return userMapper.delete(userIds);
    }
}
