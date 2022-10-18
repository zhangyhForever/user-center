package com.forever.user.web.service;

import com.forever.user.web.vo.req.UserQuery;
import com.forever.user.web.vo.req.UserReq;
import com.forever.user.web.vo.resp.UserResp;

import java.util.Collection;
import java.util.List;

public interface UserService {

    void insert(Collection<UserReq> userReqs);

    int update(UserReq userReq);

    List<UserResp> select(UserQuery userReq);

    int delete(Collection<Long> userIds);
}
