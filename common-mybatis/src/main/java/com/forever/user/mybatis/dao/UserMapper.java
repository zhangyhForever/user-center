package com.forever.user.mybatis.dao;

import com.forever.user.mybatis.entity.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface UserMapper {

    int insert(@Param("list") Collection<UserDO> user);

    int update(UserDO user);

    List<UserDO> select(@Param("userDO") UserDO userDO, @Param("start") Integer startIndex, @Param("limit") Integer pageSize);

    int delete(@Param("list") Collection<Long> userId);
}
