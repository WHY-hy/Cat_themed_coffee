package com.Cat_themed_coffee.user.service;

import java.util.List;
import com.Cat_themed_coffee.user.domain.Empl;


/**
 * 员工信息Service接口
 * 
 * @author @WHY
 * @date 2024-10-26
 */
public interface IEmplService 
{
    /**
     * 查询员工信息
     * 
     * @param id 员工信息主键
     * @return 员工信息
     */
    public Empl selectEmplById(Long id);

    /**
     * 查询员工信息列表
     * 
     * @param empl 员工信息
     * @return 员工信息集合
     */
    public List<Empl> selectEmplList(Empl empl);

    /**
     * 新增员工信息
     * 
     * @param empl 员工信息
     * @return 结果
     */
    public int insertEmpl(Empl empl);

    /**
     * 修改员工信息
     * 
     * @param empl 员工信息
     * @return 结果
     */
    public int updateEmpl(Empl empl);

    /**
     * 批量删除员工信息
     * 
     * @param ids 需要删除的员工信息主键集合
     * @return 结果
     */
    public int deleteEmplByIds(Long[] ids);

    /**
     * 删除员工信息信息
     * 
     * @param id 员工信息主键
     * @return 结果
     */
    public int deleteEmplById(Long id);
}
