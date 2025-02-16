package com.Cat_themed_coffee.user.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.Cat_themed_coffee.common.annotation.Excel;
import com.Cat_themed_coffee.common.core.domain.BaseEntity;

/**
 * 员工信息对象 tb_empl
 * 
 * @author @WHY
 * @date 2024-10-26
 */
public class Empl extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 头像 */
    @Excel(name = "头像")
    private String avatar;

    /** 员工账号 */
    @Excel(name = "员工账号")
    private String employeeAccount;

    /** 员工昵称 */
    @Excel(name = "员工昵称")
    private String employeeNickname;

    /** 员工类型 */
    private String employeeType;

    /** 员工职位 */
    @Excel(name = "员工职位")
    private String employeePosition;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 电话 */
    @Excel(name = "电话")
    private String phonenumber;

    /** 性别 */
    private String sex;

    /** 密码 */
    private String password;

    /** 状态 */
    @Excel(name = "状态")
    private String accountStatus;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setEmployeeAccount(String employeeAccount) 
    {
        this.employeeAccount = employeeAccount;
    }

    public String getEmployeeAccount() 
    {
        return employeeAccount;
    }
    public void setEmployeeNickname(String employeeNickname) 
    {
        this.employeeNickname = employeeNickname;
    }

    public String getEmployeeNickname() 
    {
        return employeeNickname;
    }
    public void setEmployeeType(String employeeType) 
    {
        this.employeeType = employeeType;
    }

    public String getEmployeeType() 
    {
        return employeeType;
    }
    public void setEmployeePosition(String employeePosition) 
    {
        this.employeePosition = employeePosition;
    }

    public String getEmployeePosition() 
    {
        return employeePosition;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setPhonenumber(String phonenumber) 
    {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() 
    {
        return phonenumber;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setAccountStatus(String accountStatus) 
    {
        this.accountStatus = accountStatus;
    }

    public String getAccountStatus() 
    {
        return accountStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("avatar", getAvatar())
            .append("employeeAccount", getEmployeeAccount())
            .append("employeeNickname", getEmployeeNickname())
            .append("employeeType", getEmployeeType())
            .append("employeePosition", getEmployeePosition())
            .append("email", getEmail())
            .append("phonenumber", getPhonenumber())
            .append("sex", getSex())
            .append("password", getPassword())
            .append("accountStatus", getAccountStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
