package com.bus.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bus.server.pojo.CommonResult;
import com.bus.server.pojo.SysUser;
import com.bus.server.pojo.UserLoginParam;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author stx
 * @since 2021-12-23
 */
public interface ISysUserService extends IService<SysUser> {

    CommonResult login(UserLoginParam param, HttpServletRequest request);

    SysUser getUserByName(String userName);

    int updateUserPassword(String oldPassword, String newPassword, Integer userId);

}
