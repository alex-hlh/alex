package com.bus.server.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bus.server.mapper.SysMenuMapper;
import com.bus.server.pojo.SysMenu;
import com.bus.server.pojo.SysUser;
import com.bus.server.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author stx
 * @since 2021-12-23
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据id获取菜单列表
     * @return
     */
    @Override
    public List<SysMenu> getMenusById() {
        Long id = ((SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        //从redis中获取菜单
        List<SysMenu> menus = (List<SysMenu>)valueOperations.get("MENU_"+id);
        if(CollectionUtil.isEmpty(menus)){
           menus = sysMenuMapper.getMenusById(id);
           //首次查询放入redis
            valueOperations.set("MENU_"+id,menus);
        }
        return menus;
    }

    /**
     * 根据角色获取菜单列表
     * @return
     */
    @Override
    public List<SysMenu> getMenusByRole() {
        return sysMenuMapper.getMenusByRole();
    }
}
