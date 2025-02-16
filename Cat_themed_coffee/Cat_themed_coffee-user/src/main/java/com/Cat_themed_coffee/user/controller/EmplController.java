package com.Cat_themed_coffee.user.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.Cat_themed_coffee.user.domain.Empl;
import com.Cat_themed_coffee.user.service.IEmplService;
import com.Cat_themed_coffee.common.utils.poi.ExcelUtil;
import com.Cat_themed_coffee.common.core.page.TableDataInfo;

/**
 * 员工信息Controller
 * 
 * @author @WHY
 * @date 2024-10-26
 */
@RestController
@RequestMapping("/user/empl")
public class EmplController extends BaseController
{
    @Autowired
    private IEmplService emplService;

    /**
     * 查询员工信息列表
     */
    @PreAuthorize("@ss.hasPermi('user:empl:list')")
    @GetMapping("/list")
    public TableDataInfo list(Empl empl)
    {
        startPage();
        List<Empl> list = emplService.selectEmplList(empl);
        return getDataTable(list);
    }

    /**
     * 导出员工信息列表
     */
    @PreAuthorize("@ss.hasPermi('user:empl:export')")
    @Log(title = "员工信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Empl empl)
    {
        List<Empl> list = emplService.selectEmplList(empl);
        ExcelUtil<Empl> util = new ExcelUtil<Empl>(Empl.class);
        util.exportExcel(response, list, "员工信息数据");
    }

    /**
     * 获取员工信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('user:empl:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(emplService.selectEmplById(id));
    }

    /**
     * 新增员工信息
     */
    @PreAuthorize("@ss.hasPermi('user:empl:add')")
    @Log(title = "员工信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Empl empl)
    {
        return toAjax(emplService.insertEmpl(empl));
    }

    /**
     * 修改员工信息
     */
    @PreAuthorize("@ss.hasPermi('user:empl:edit')")
    @Log(title = "员工信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Empl empl)
    {
        return toAjax(emplService.updateEmpl(empl));
    }

    /**
     * 删除员工信息
     */
    @PreAuthorize("@ss.hasPermi('user:empl:remove')")
    @Log(title = "员工信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(emplService.deleteEmplByIds(ids));
    }
}
