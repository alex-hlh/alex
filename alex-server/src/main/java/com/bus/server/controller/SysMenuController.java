package com.bus.server.controller;


import com.bus.server.pojo.SysMenu;
import com.bus.server.service.ISysMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author stx
 * @since 2021-12-23
 */
@RestController
@RequestMapping("/sys-menu")
public class SysMenuController {

    @Autowired
    private ISysMenuService iSysMenuService;

    @ApiOperation("通过userid获取菜单menu")
    @GetMapping("/getMenus")
    public List<SysMenu> getMenusById() {
        return iSysMenuService.getMenusById();
    }

}
