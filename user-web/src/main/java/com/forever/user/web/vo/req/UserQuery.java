package com.forever.user.web.vo.req;

import com.forever.user.mybatis.entity.UserDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel("用户信息")
public class UserQuery implements Serializable {

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty(value = "用户名", example = "username")
    private String username;

    @ApiModelProperty(value = "用户密码", example = "Abc123")
    private String pwd;

    @ApiModelProperty(value = "用户邮箱", example = "1234@mail.com")
    private String email;

    @ApiModelProperty(value = "用户电话号码", example = "123456789101")
    private Long phone;

    @ApiModelProperty(value = "页码", example = "1")
    @NotNull(message = "页码不能为空")
    @Min(1)
    private Integer page;

    @ApiModelProperty(value = "每页展示数量", example = "50")
    @NotNull(message = "页码大小不能为空")
    @Max(100)
    private Integer pageSize;

    public UserDO toDO() {
        UserDO userDO = new UserDO();
        userDO.setId(this.getId());
        userDO.setUsername(this.getUsername());
        userDO.setPwd(this.getPwd());
        userDO.setEmail(this.getEmail());
        userDO.setPhone(this.getPhone());
        return userDO;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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
