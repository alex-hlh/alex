package com.bus.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bus.server.mapper.SysLoginLogMapper;
import com.bus.server.pojo.SysLoginLog;
import com.bus.server.service.ISysLoginLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author stx
 * @since 2021-12-23
 */
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements ISysLoginLogService {

}
