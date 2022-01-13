package com.bus.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bus.server.pojo.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author stx
 * @since 2021-12-23
 */
public interface ISysMenuService extends IService<SysMenu> {
    /**
     * 根据id获取菜单列表
     *
     * @return
     */
    List<SysMenu> getMenusById();

    /**
     * 根据角色获取菜单列表
     *
     * @return
     */
    List<SysMenu> getMenusByRole();
}
