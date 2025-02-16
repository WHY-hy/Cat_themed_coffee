package com.Cat_themed_coffee.framework.web.service;

import com.Cat_themed_coffee.common.core.domain.entity.Customer;
import com.Cat_themed_coffee.common.core.domain.model.LoginUser;
import com.Cat_themed_coffee.common.enums.UserStatus;
import com.Cat_themed_coffee.common.exception.ServiceException;
import com.Cat_themed_coffee.common.utils.MessageUtils;
import com.Cat_themed_coffee.common.utils.StringUtils;
import com.Cat_themed_coffee.user.mapper.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 用户验证处理
 *
 * @author why
 */
@Component("CustomerDetailsServiceImpl")
public class CustomerDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(CustomerDetailsServiceImpl.class);

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerPasswordService customerPasswordService;

    @Override
    public UserDetails loadUserByUsername(String customerName) throws UsernameNotFoundException
    {
        Customer user = customerMapper.selectCustomerByUserName(customerName);
        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", customerName);
            throw new ServiceException(MessageUtils.message("user.not.exists"));
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            log.info("登录用户：{} 已被删除.", customerName);
            throw new ServiceException(MessageUtils.message("user.password.delete"));
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            log.info("登录用户：{} 已被停用.", customerName);
            throw new ServiceException(MessageUtils.message("user.blocked"));
        }

        customerPasswordService.validate(user);

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(Customer user)
    {
        return new LoginUser(user.getId(), user);
    }
}
