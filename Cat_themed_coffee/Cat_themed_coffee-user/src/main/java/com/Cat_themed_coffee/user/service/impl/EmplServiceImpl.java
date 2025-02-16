package com.Cat_themed_coffee.user.service.impl;

import java.util.List;
import com.Cat_themed_coffee.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Cat_themed_coffee.user.mapper.EmplMapper;
import com.Cat_themed_coffee.user.domain.Empl;
import com.Cat_themed_coffee.user.service.IEmplService;

/**
 * 员工信息Service业务层处理
 * 
 * @author @WHY
 * @date 2024-10-26
 */
@Service
public class EmplServiceImpl implements IEmplService 
{
    @Autowired
    private EmplMapper emplMapper;

    /**
     * 查询员工信息
     * 
     * @param id 员工信息主键
     * @return 员工信息
     */
    @Override
    public Empl selectEmplById(Long id)
    {
        return emplMapper.selectEmplById(id);
    }

    /**
     * 查询员工信息列表
     * 
     * @param empl 员工信息
     * @return 员工信息
     */
    @Override
    public List<Empl> selectEmplList(Empl empl)
    {
        return emplMapper.selectEmplList(empl);
    }

    /**
     * 新增员工信息
     * 
     * @param empl 员工信息
     * @return 结果
     */
    @Override
    public int insertEmpl(Empl empl)
    {
        empl.setCreateTime(DateUtils.getNowDate());
        return emplMapper.insertEmpl(empl);
    }

    /**
     * 修改员工信息
     * 
     * @param empl 员工信息
     * @return 结果
     */
    @Override
    public int updateEmpl(Empl empl)
    {
        empl.setUpdateTime(DateUtils.getNowDate());
        return emplMapper.updateEmpl(empl);
    }

    /**
     * 批量删除员工信息
     * 
     * @param ids 需要删除的员工信息主键
     * @return 结果
     */
    @Override
    public int deleteEmplByIds(Long[] ids)
    {
        return emplMapper.deleteEmplByIds(ids);
    }

    /**
     * 删除员工信息信息
     * 
     * @param id 员工信息主键
     * @return 结果
     */
    @Override
    public int deleteEmplById(Long id)
    {
        return emplMapper.deleteEmplById(id);
    }
}
