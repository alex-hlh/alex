package com.bus.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bus.server.pojo.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author stx
 * @since 2021-12-23
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 根据id查询菜单
     *
     * @param id
     * @return
     */
    List<SysMenu> getMenusById(Long id);

    /**
     * 根据角色获取菜单列表
     *
     * @return
     */
    List<SysMenu> getMenusByRole();
}
