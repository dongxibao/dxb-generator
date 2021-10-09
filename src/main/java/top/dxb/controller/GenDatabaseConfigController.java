package top.dxb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.dxb.entity.sys.DatabaseConfig;
import top.dxb.service.GenDatabaseConfigService;
import top.dxb.utils.PageUtils;
import top.dxb.utils.Query;
import top.dxb.utils.R;

import java.util.List;
import java.util.Map;

/**
 * 数据库配置
 *
 * @author Dongxibao
 * @date 2020-12-30
 */
@Slf4j
@RestController
@RequestMapping("sys/databaseconfig")
public class GenDatabaseConfigController {
    @Autowired
    private GenDatabaseConfigService genDatabaseConfigService;

    /**
     * 查询数据库配置
     */
    @GetMapping
    public R page(@RequestParam Map<String, Object> params) {
        PageUtils pageUtils = genDatabaseConfigService.selectByCondition(new Query(params));
        return R.ok().put("page", pageUtils);
    }

    /**
     * 获取数据库配置详细信息
     */
    @GetMapping("/{id}")
    public R get(@PathVariable("id") Long id) {
        return R.ok().put("data", genDatabaseConfigService.getById(id));
    }

    /**
     * 新增数据库配置
     */
    @PostMapping(value = "save")
    public R insert(@RequestBody DatabaseConfig genDatabaseConfig) {
        genDatabaseConfig = genDatabaseConfigService.save(genDatabaseConfig);
        return R.ok();
    }

    /**
     * 修改数据库配置
     */
    @PostMapping("update")
    public R update(@RequestBody DatabaseConfig genDatabaseConfig) {
//        genDatabaseConfig.setId(Integer.valueOf(id));
        genDatabaseConfig = genDatabaseConfigService.updateById(genDatabaseConfig);
        return R.ok();
    }

    /**
     * 删除数据库配置
     */
    @DeleteMapping
    public R delete(@RequestBody List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return R.error(400, "id为空");
        }
        boolean delete = genDatabaseConfigService.deleteBatchIds(ids);
        return R.ok();
    }
}
