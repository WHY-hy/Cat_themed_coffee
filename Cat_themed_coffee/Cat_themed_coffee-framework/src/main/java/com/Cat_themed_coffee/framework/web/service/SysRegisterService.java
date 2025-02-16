package com.Cat_themed_coffee.framework.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.Cat_themed_coffee.common.constant.CacheConstants;
import com.Cat_themed_coffee.common.constant.Constants;
import com.Cat_themed_coffee.common.constant.UserConstants;
import com.Cat_themed_coffee.common.core.domain.entity.SysUser;
import com.Cat_themed_coffee.common.core.domain.model.RegisterBody;
import com.Cat_themed_coffee.common.core.redis.RedisCache;
import com.Cat_themed_coffee.common.exception.user.CaptchaException;
import com.Cat_themed_coffee.common.exception.user.CaptchaExpireException;
import com.Cat_themed_coffee.common.utils.MessageUtils;
import com.Cat_themed_coffee.common.utils.SecurityUtils;
import com.Cat_themed_coffee.common.utils.StringUtils;
import com.Cat_themed_coffee.framework.manager.AsyncManager;
import com.Cat_themed_coffee.framework.manager.factory.AsyncFactory;
import com.Cat_themed_coffee.system.service.ISysConfigService;
import com.Cat_themed_coffee.system.service.ISysUserService;

import java.util.regex.Pattern;

import static com.Cat_themed_coffee.common.constant.UserConstants.EMAIL_LENGTH;

/**
 * 注册校验方法
 * 
 * @author ruoyi
 */
@Component
public class SysRegisterService
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 注册
     */
    public String register(RegisterBody registerBody)
    {
        String msg = "", username = registerBody.getUsername(), phonenumber = registerBody.getPhonenumber(),emile = registerBody.getEmail(),password = registerBody.getPassword();
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);
        sysUser.setPhonenumber(phonenumber);
        sysUser.setEmail(emile);
        // 验证码开关
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled)
        {
            validateCaptcha(username, registerBody.getCode(), registerBody.getUuid());
        }

        if (StringUtils.isEmpty(username))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(phonenumber))
        {
            msg = "手机号不能为空";
        }
        else if (StringUtils.isEmpty(emile))
        {
            msg = "邮箱不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (phonenumber.length() != UserConstants.PHONE_NUMBER_LENGTH)
        {
            msg = "请输入正确的11位手机号码";
        }
        else if (!Pattern.matches(EMAIL_LENGTH,emile))
        {
            msg = "请输入正确的邮箱地址";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }

        else if (!userService.checkUserNameUnique(sysUser))
        {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        }
        else
        {
            sysUser.setNickName(username);
            sysUser.setPassword(SecurityUtils.encryptPassword(password));
            boolean regFlag = userService.registerUser(sysUser);
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }

    /**
     * 校验验证码
     * 
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException();
        }
    }
}
