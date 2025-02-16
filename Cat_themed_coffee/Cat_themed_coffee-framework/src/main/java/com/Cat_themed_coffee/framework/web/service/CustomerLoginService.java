package com.Cat_themed_coffee.framework.web.service;

import com.Cat_themed_coffee.common.constant.Constants;
import com.Cat_themed_coffee.common.constant.UserConstants;
import com.Cat_themed_coffee.common.core.domain.model.LoginUser;
import com.Cat_themed_coffee.common.core.redis.RedisCache;
import com.Cat_themed_coffee.common.exception.ServiceException;
import com.Cat_themed_coffee.common.exception.user.*;
import com.Cat_themed_coffee.common.utils.DateUtils;
import com.Cat_themed_coffee.common.utils.MessageUtils;
import com.Cat_themed_coffee.common.utils.StringUtils;
import com.Cat_themed_coffee.common.utils.ip.IpUtils;
import com.Cat_themed_coffee.common.core.domain.entity.Customer;
import com.Cat_themed_coffee.user.service.ICustomerService;
import com.Cat_themed_coffee.framework.manager.AsyncManager;
import com.Cat_themed_coffee.framework.manager.factory.AsyncFactory;
import com.Cat_themed_coffee.framework.security.context.AuthenticationContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * 登录校验方法
 *
 * @author why
 */
@Component
public class CustomerLoginService
{
    // 是否允许账户多终端同时登录（true允许 false不允许）
  /*  @Value("${token.soloLogin}")
    private boolean soloLogin;*/

    @Autowired
    private RedisCache redisCache;

    @Resource
    @Qualifier("MemberAuthenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    private ICustomerService customerUerService;

    @Autowired
    private TokenService tokenService;


    /**
     * 登录验证
     *
     * @param customerName 用户名
     * @param password 密码
     * @return 结果
     */
    public String customerLogin(String customerName, String password)
    {
        // 登录前置校验
        loginPreCheck(customerName, password);
        Customer user = null;
        // 用户验证
        Authentication authentication = null;

        try
        {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customerName, password);
            System.out.println("authenticationToken");
            System.out.println(authenticationToken);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用CustomerDetailsServiceImpl.loadUserByUsernam
            authentication = authenticationManager.authenticate(authenticationToken);
            System.out.println("authentication");
            System.out.println(authentication);
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(customerName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(customerName, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }
        finally
        {
            AuthenticationContextHolder.clearContext();
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(customerName, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());
        /*if (!soloLogin)
        {
            // 如果用户不允许多终端同时登录，清除缓存信息
            String userIdKey = Constants.LOGIN_USERID_KEY + loginUser.getCustomer().getId();
            String userKey = redisCache.getCacheObject(userIdKey);
            if (StringUtils.isNotEmpty(userKey))
            {
                redisCache.deleteObject(userIdKey);
                redisCache.deleteObject(userKey);
            }
        }*/
        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 登录前置校验
     * @param customerName 用户名
     * @param password 用户密码
     */
    public void loginPreCheck(String customerName, String password)
    {
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(customerName) || StringUtils.isEmpty(password))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(customerName, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(customerName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
        // 用户名不在指定范围内 错误
        if (customerName.length() < UserConstants.USERNAME_MIN_LENGTH
                || customerName.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(customerName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
    }

    /**
     * 记录登录信息
     *
     * @param id 用户ID
     */
    public void recordLoginInfo(Long id)
    {
        Customer customerUser = new Customer();
        customerUser.setId(id);
        customerUser.setLoginIp(IpUtils.getIpAddr());
        customerUser.setLoginDate(DateUtils.getNowDate());
        /*customerUerService.updateUserProfile(customerUser);*/
    }
}
