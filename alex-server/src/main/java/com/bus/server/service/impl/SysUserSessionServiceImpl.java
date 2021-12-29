package com.bus.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bus.server.mapper.SysUserSessionMapper;
import com.bus.server.pojo.SysUserSession;
import com.bus.server.service.ISysUserSessionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户在线 Session 服务实现类
 * </p>
 *
 * @author stx
 * @since 2021-12-23
 */
@Service
public class SysUserSessionServiceImpl extends ServiceImpl<SysUserSessionMapper, SysUserSession> implements ISysUserSessionService {

}
