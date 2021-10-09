package top.dxb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.dxb.dao.sys.SysConfigMapper;
import top.dxb.entity.sys.SysConfig;
import top.dxb.utils.PageUtils;
import top.dxb.utils.Query;

import java.util.List;

/**
 * 系统配置
 *
 * @author Dongxibao
 * @date 2020-12-30
 */
@Slf4j
@Service
public class GenSysConfigService {
    @Autowired
    private SysConfigMapper sysConfigMapper;

    public SysConfig getById(Long id) {
        SysConfig genSysConfig = sysConfigMapper.selectById(id);
        return genSysConfig;
    }


    public PageUtils selectByCondition(Query query) {
        List<SysConfig> list = sysConfigMapper.selectByCondition(null);
        return new PageUtils(list, list.size(), query.getLimit(), query.getPage());
    }

    @Transactional(rollbackFor = Exception.class)
    public SysConfig save(SysConfig genSysConfig) {
        sysConfigMapper.insert(genSysConfig);
        return genSysConfig;
    }

    @Transactional(rollbackFor = Exception.class)
    public SysConfig updateById(SysConfig genSysConfig) {
        sysConfigMapper.update(genSysConfig);
        return genSysConfig;
    }

    public boolean removeById(Long id) {
        sysConfigMapper.deleteById(id);
        return true;
    }

    public boolean deleteByIds(Long[] ids) {
        sysConfigMapper.deleteByIds(ids);
        return true;
    }
}

