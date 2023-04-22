package com.cy.store.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;


/**用户实体类  SpringBoot约定大于配置*/
@ApiModel("用户实体类")
public class User extends BaseEntity  {
    /***/
    @ApiModelProperty(value = "用户id")
    private Integer uid;
    /***/
    @ApiModelProperty(value = "用户名")
//    @NotNull
//    @Length(max = 11, min = 1, message = "长度必须大于等于1或小于等于11")
    private String username;
    @ApiModelProperty(value = "密码")
    /***/
    private String password;
    /***/
    @ApiModelProperty(value = "盐值")
    private String salt;
    /***/
    @ApiModelProperty(value = "电话号码")
    private String phone;
    /***/
    @ApiModelProperty(value = "电子邮箱")
    private String email;
    /***/
    @ApiModelProperty(value = "性别:0-女，1-男")
    private Integer gender;
    /***/
    @ApiModelProperty(value = "头像")
    private String avatar;
    /***/
    @ApiModelProperty(value = "是否删除：0-未删除，1-已删除")
    private Integer isDelete;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(salt, user.salt) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email) && Objects.equals(gender, user.gender) && Objects.equals(avatar, user.avatar) && Objects.equals(isDelete, user.isDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), uid, username, password, salt, phone, email, gender, avatar, isDelete);
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", avatar='" + avatar + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}
