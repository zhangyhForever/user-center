package com.forever.user.web.vo.req;

import com.forever.user.mybatis.entity.UserDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@ApiModel("用户信息")
public class UserReq implements Serializable {

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty(value = "用户名", example = "username")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "用户密码", example = "Abc123")
    private String pwd;

    @ApiModelProperty(value = "用户邮箱", example = "1234@mail.com")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "用户电话号码", example = "123456789101")
    @NotNull(message = "电话号码不能为空")
    private Long phone;

    public UserDO toDO() {
        UserDO userDO = new UserDO();
        userDO.setId(this.getId());
        userDO.setUsername(this.getUsername());
        userDO.setPwd(this.getPwd());
        userDO.setEmail(this.getEmail());
        userDO.setPhone(this.getPhone());
        return userDO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

}
