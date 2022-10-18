package com.forever.user.web.vo.resp;

import com.forever.user.mybatis.entity.UserDO;
import org.apache.catalina.User;

import java.io.Serializable;
import java.util.Objects;

public class UserResp implements Serializable {

    private Long id;

    private String username;

    private String pwd;

    private String email;

    private Long phone;

    public static UserResp of(UserDO userDO) {
        if (Objects.isNull(userDO)) {
            return null;
        }
        UserResp userResp = new UserResp();
        userResp.setId(userDO.getId());
        userResp.setEmail(userDO.getEmail());
        userResp.setUsername(userDO.getUsername());
        userResp.setPhone(userDO.getPhone());
        userResp.setPwd(userDO.getPwd());
        return userResp;
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

    @Override
    public String toString() {
        return "UserResp{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                '}';
    }
}
