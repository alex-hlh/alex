package com.bus.server.controller;

import com.bus.server.pojo.RespBean;
import com.bus.server.pojo.SysUser;
import com.bus.server.pojo.UserLoginParam;
import com.bus.server.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @ClassName: LoginController
 * @Description:
 * @Author: hlh
 * @Date: 2021-12-25 13:35
 */
@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private ISysUserService iSysUserService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody UserLoginParam param, HttpServletRequest request) {
        return iSysUserService.login(param, request);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @PostMapping("/sysuser/info")
    public SysUser getSysUserInfo(Principal principal) {
        if (principal == null) {
            return null;
        }
        String userName = principal.getName();
        SysUser sysUser = iSysUserService.getUserByName(userName);
        sysUser.setPassword(null);
        return sysUser;
    }

    @ApiOperation(value = "退出登录！")
    @PostMapping("/logout")
    public RespBean logout() {
        return RespBean.success("退出成功！");
    }

}
