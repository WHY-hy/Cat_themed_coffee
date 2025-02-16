package com.Cat_themed_coffee.user.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.Cat_themed_coffee.common.core.domain.entity.SysUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Cat_themed_coffee.common.annotation.Log;
import com.Cat_themed_coffee.common.core.controller.BaseController;
import com.Cat_themed_coffee.common.core.domain.AjaxResult;
import com.Cat_themed_coffee.common.enums.BusinessType;
import com.Cat_themed_coffee.common.core.domain.entity.Customer;
import com.Cat_themed_coffee.user.service.ICustomerService;
import com.Cat_themed_coffee.common.utils.poi.ExcelUtil;
import com.Cat_themed_coffee.common.core.page.TableDataInfo;

/**
 * 客户信息Controller
 * 
 * @author @WHY
 * @date 2024-10-31
 */
@RestController
@RequestMapping("/user/customer")
public class CustomerController extends BaseController
{
    @Autowired
    private ICustomerService customerService;

    /**
     * 查询客户信息列表
     */
    @PreAuthorize("@ss.hasPermi('user:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(Customer customer)
    {
        startPage();
        List<Customer> list = customerService.selectCustomerList(customer);
        return getDataTable(list);
    }

    /**
     * 导出客户信息列表
     */
    @PreAuthorize("@ss.hasPermi('user:customer:export')")
    @Log(title = "客户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Customer customer)
    {
        List<Customer> list = customerService.selectCustomerList(customer);
        ExcelUtil<Customer> util = new ExcelUtil<Customer>(Customer.class);
        util.exportExcel(response, list, "客户信息数据");
    }

    /**
     * 获取客户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('user:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(customerService.selectCustomerById(id));
    }

    /**
     * 新增客户信息
     */
    @PreAuthorize("@ss.hasPermi('user:customer:add')")
    @Log(title = "客户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Customer customer)
    {
        return toAjax(customerService.insertCustomer(customer));
    }

    /**
     * 修改客户信息
     */
    @PreAuthorize("@ss.hasPermi('user:customer:edit')")
    @Log(title = "客户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Customer customer)
    {
        return toAjax(customerService.updateCustomer(customer));
    }

    /**
     * 删除客户信息
     */
    @PreAuthorize("@ss.hasPermi('user:customer:remove')")
    @Log(title = "客户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(customerService.deleteCustomerByIds(ids));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('user:customer:edit')")
    @Log(title = "客户信息", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody Customer customer)
    {
       /* user.setUpdateBy(getUsername());*/
        return toAjax(customerService.updateCustomerStatus(customer));
    }

  /*  @PreAuthorize("@ss.hasPermi('user:customer:edit')")
    @Log(title = "客户信息", businessType = BusinessType.UPDATE)
    @PutMapping("/authRole")
    public AjaxResult insertAuthRole(Long userId, Long[] roleIds) {
        customerService.insertCustomerAuth(userId, roleIds);
        return success();
    }*/
}

