package com.Cat_themed_coffee.user.mapper;

import java.util.List;
import com.Cat_themed_coffee.user.domain.Empl;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工信息Mapper接口
 * 
 * @author @WHY
 * @date 2024-10-26
 */
@Mapper
public interface EmplMapper 
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
     * 删除员工信息
     * 
     * @param id 员工信息主键
     * @return 结果
     */
    public int deleteEmplById(Long id);

    /**
     * 批量删除员工信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEmplByIds(Long[] ids);
}
