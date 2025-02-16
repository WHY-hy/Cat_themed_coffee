package com.Cat_themed_coffee.web.controller.user;

import com.Cat_themed_coffee.common.constant.Constants;
import com.Cat_themed_coffee.common.core.domain.AjaxResult;
import com.Cat_themed_coffee.common.core.domain.entity.Customer;
import com.Cat_themed_coffee.common.core.domain.model.LoginBody;
import com.Cat_themed_coffee.common.utils.SecurityUtils;
import com.Cat_themed_coffee.framework.web.service.CustomerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录验证
 *
 * @author why
 */
@RestController
@RequestMapping("/customer/info")
public class CustomerLoginController
{
    @Autowired
    private CustomerLoginService customerLoginService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/customerLogin")
    public AjaxResult customerLogin(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = customerLoginService.customerLogin(loginBody.getUsername(), loginBody.getPassword());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getCustomerInfo")
    public AjaxResult getCustomerInfo()
    {
        Customer user = SecurityUtils.getLoginUser().getCustomer();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        return ajax;
    }

}
