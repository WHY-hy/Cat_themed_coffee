package com.Cat_themed_coffee.user.mapper;

import java.util.List;
import com.Cat_themed_coffee.common.core.domain.entity.Customer;

/**
 * 客户信息Mapper接口
 * 
 * @author @WHY
 * @date 2024-10-31
 */
public interface CustomerMapper 
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
     * 删除客户信息
     * 
     * @param id 客户信息主键
     * @return 结果
     */
    public int deleteCustomerById(Long id);

    /**
     * 批量删除客户信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerByIds(Long[] ids);

    Customer selectCustomerByUserName(String customerName);
}
