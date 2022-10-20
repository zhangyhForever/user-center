package com.forever.user.web.controller;

import com.forever.user.web.common.ResponseResult;
import com.forever.user.web.service.UserService;
import com.forever.user.web.vo.req.UserQuery;
import com.forever.user.web.vo.req.UserReq;
import com.forever.user.web.vo.resp.UserResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api("用户中心")
@Validated
public class UserController {

    @Resource
    UserService userService;

    @ApiOperation("注册用户")
    @PostMapping("/register")
    public ResponseResult registerUser(@RequestBody @Valid List<UserReq> userReqs) {
        userService.insert(userReqs);
        return ResponseResult.OK();
    }

    @ApiOperation("修改用户")
    @PostMapping("/modify")
    public ResponseResult modifyUser(@RequestBody UserReq userReq) {
        userService.update(userReq);
        return ResponseResult.OK("success");
    }

    @ApiOperation("查询用户")
    @GetMapping("/select")
    public ResponseResult selectUser(@Valid UserQuery userReq) {
        List<UserResp> userResps = userService.select(userReq);
        return ResponseResult.OK(userResps);
    }

    @ApiOperation("删除用户")
    @PostMapping("/delete")
    public ResponseResult delUser(@RequestBody List<Long> userIds) {
        userService.delete(userIds);
        return ResponseResult.OK("success");
    }
}
