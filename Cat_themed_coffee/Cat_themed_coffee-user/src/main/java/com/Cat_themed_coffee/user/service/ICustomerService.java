package com.Cat_themed_coffee.user.service;

import java.util.List;
import com.Cat_themed_coffee.common.core.domain.entity.Customer;
import com.Cat_themed_coffee.common.core.domain.entity.SysUser;

/**
 * 客户信息Service接口
 * 
 * @author @WHY
 * @date 2024-10-31
 */
public interface ICustomerService 
{
    /**
     * 查询客户信息
     * 
     * @param id 客户信息主键
     * @return 客户信息
     */
    public Customer selectCustomerById(Long id);

    /**
     * 查询客户信息列表
     * 
     * @param customer 客户信息
     * @return 客户信息集合
     */
    public List<Customer> selectCustomerList(Customer customer);

    /**
     * 新增客户信息
     * 
     * @param customer 客户信息
     * @return 结果
     */
    public int insertCustomer(Customer customer);

    /**
     * 修改客户信息
     * 
     * @param customer 客户信息
     * @return 结果
     */
    public int updateCustomer(Customer customer);

    /**
     * 批量删除客户信息
     * 
     * @param ids 需要删除的客户信息主键集合
     * @return 结果
     */
    public int deleteCustomerByIds(Long[] ids);

    /**
     * 删除客户信息信息
     * 
     * @param id 客户信息主键
     * @return 结果
     */
    public int deleteCustomerById(Long id);

    /**
     * 用户授权角色
     *
     * @param userId 用户ID
     * @param roleIds 角色组
     */
    /*public void insertCustomerAuth(Long userId, Long[] roleIds);*/

    /**
     * 修改用户状态
     *
     * @param customer 用户信息
     * @return 结果
     */
    public int updateCustomerStatus(Customer customer);
}

