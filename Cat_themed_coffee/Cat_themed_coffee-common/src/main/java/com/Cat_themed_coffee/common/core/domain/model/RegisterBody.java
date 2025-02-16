package com.Cat_themed_coffee.common.core.domain.model;

/**
 * 用户注册对象
 * 
 * @author ruoyi
 */
public class RegisterBody extends LoginBody
{

    /**
     * 手机号
     */
    private String phonenumber;

    /**
     * 邮箱
     */
    private String email;

    public String getPhonenumber() { return phonenumber;}
    public void setPhonenumber(String phonenumber) { this.phonenumber = phonenumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
