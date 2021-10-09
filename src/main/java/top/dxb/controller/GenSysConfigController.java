package top.dxb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.dxb.entity.sys.SysConfig;
import top.dxb.service.GenSysConfigService;
import top.dxb.utils.PageUtils;
import top.dxb.utils.Query;
import top.dxb.utils.R;

import java.util.Date;
import java.util.Map;

/**
 * 系统配置
 *
 * @author Dongxibao
 * @date 2020-12-30
 */
@RestController
@RequestMapping("sys/sysconfig")
public class GenSysConfigController {
    @Autowired
    private GenSysConfigService genSysConfigService;

    /**
     * 查询系统配置
     */
    @GetMapping
    public R page(@RequestParam Map<String, Object> params) {
        PageUtils pageUtils = genSysConfigService.selectByCondition(new Query(params));
        return R.ok().put("page", pageUtils);
    }

    /**
     * 获取系统配置详细信息
     */
    @GetMapping("/{id}")
    public R get(@PathVariable("id") Long id) {
        return R.ok().put("data", genSysConfigService.getById(id));
    }

    /**
     * 新增系统配置
     */
    @PostMapping("save")
    public R insert(@RequestBody SysConfig genSysConfig) {
        genSysConfig.setCreateTime(new Date());
        genSysConfig = genSysConfigService.save(genSysConfig);
        return R.ok();
    }

    /**
     * 修改系统配置
     */
    @PostMapping("/update")
    public R update(@RequestBody SysConfig genSysConfig) {
        genSysConfig = genSysConfigService.updateById(genSysConfig);
        return R.ok();
    }

    /**
     * 删除系统配置
     */
    @DeleteMapping
    public R delete(@RequestBody Long[] ids) {
        if (ids == null || ids.length < 1) {
            return R.error(400, "id为空");
        }
        boolean delete = genSysConfigService.deleteByIds(ids);
        return R.ok();
    }
}
