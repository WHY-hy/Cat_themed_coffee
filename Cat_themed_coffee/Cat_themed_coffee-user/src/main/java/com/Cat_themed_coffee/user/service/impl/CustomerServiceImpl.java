package com.Cat_themed_coffee.user.service.impl;

import java.util.List;

import com.Cat_themed_coffee.common.core.domain.entity.SysUser;
import com.Cat_themed_coffee.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Cat_themed_coffee.user.mapper.CustomerMapper;
import com.Cat_themed_coffee.common.core.domain.entity.Customer;
import com.Cat_themed_coffee.user.service.ICustomerService;


/**
 * 客户信息Service业务层处理
 * 
 * @author @WHY
 * @date 2024-10-31
 */
@Service
public class CustomerServiceImpl implements ICustomerService 
{
    @Autowired
    private CustomerMapper customerMapper;


    /**
     * 查询客户信息
     * 
     * @param id 客户信息主键
     * @return 客户信息
     */
    @Override
    public Customer selectCustomerById(Long id)
    {
        return customerMapper.selectCustomerById(id);
    }

    /**
     * 查询客户信息列表
     * 
     * @param customer 客户信息
     * @return 客户信息
     */
    @Override
    public List<Customer> selectCustomerList(Customer customer)
    {
        return customerMapper.selectCustomerList(customer);
    }

    /**
     * 新增客户信息
     * 
     * @param customer 客户信息
     * @return 结果
     */
    @Override
    public int insertCustomer(Customer customer)
    {
        customer.setCreateTime(DateUtils.getNowDate());
        return customerMapper.insertCustomer(customer);
    }

    /**
     * 修改客户信息
     * 
     * @param customer 客户信息
     * @return 结果
     */
    @Override
    public int updateCustomer(Customer customer)
    {
        customer.setUpdateTime(DateUtils.getNowDate());
        return customerMapper.updateCustomer(customer);
    }

    /**
     * 批量删除客户信息
     * 
     * @param ids 需要删除的客户信息主键
     * @return 结果
     */
    @Override
    public int deleteCustomerByIds(Long[] ids)
    {
        return customerMapper.deleteCustomerByIds(ids);
    }

    /**
     * 删除客户信息信息
     * 
     * @param id 客户信息主键
     * @return 结果
     */
    @Override
    public int deleteCustomerById(Long id)
    {
        return customerMapper.deleteCustomerById(id);
    }



    /**
     * 修改用户状态
     *
     * @param customer 用户信息
     * @return 结果
     */
    @Override
    public int updateCustomerStatus(Customer customer)
    {
        return customerMapper.updateCustomer(customer);
    }
    /**
     * 用户授权角色
     *
     * @param userId 用户ID
     * @param roleIds 角色组
     */
   /* @Override
    @Transactional
    public void insertCustomerAuth(Long userId, Long[] roleIds) {
        userRoleMapper.deleteUserRoleByUserId(userId);
        insertUserRole(userId, roleIds);
    }

    public void insertUserRole(Long userId, Long[] roleIds) {
        if (roleIds != null && roleIds.length > 0) {
            // 新增用户与角色管理
            List<SysUserRole> list = new ArrayList<SysUserRole>(roleIds.length);
            for (Long roleId : roleIds) {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(userId);
                ur.setRoleId(roleId);
                list.add(ur);
            }
            userRoleMapper.batchUserRole(list);
        }
    }
*/

}
