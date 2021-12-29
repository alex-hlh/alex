package com.bus.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bus.server.mapper.SysRoleMenuMapper;
import com.bus.server.pojo.SysRoleMenu;
import com.bus.server.service.ISysRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author stx
 * @since 2021-12-23
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

}
