package com.bus.server.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bus.server.config.JwtTokenUtil;
import com.bus.server.mapper.SysUserMapper;
import com.bus.server.pojo.RespBean;
import com.bus.server.pojo.SysUser;
import com.bus.server.pojo.UserLoginParam;
import com.bus.server.service.ISysUserService;
import org.apache.velocity.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author stx
 * @since 2021-12-23
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 登录之后返回token
     *
     * @param param
     * @param request
     * @return
     */
    @Override
    public RespBean login(UserLoginParam param, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if(StrUtil.isEmpty(param.getCode()) && !captcha.equalsIgnoreCase(param.getCode())){
            return RespBean.error("验证码不正确！");
        }
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(param.getUsername());
        if (null == userDetails || !passwordEncoder.matches(param.getPassword(), userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确！");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("帐号已停用，请联系管理员！");
        }
        //更新security登录用户对象
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登录成功！", tokenMap);
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     */
    @Override
    public SysUser getUserByName(String userName) {
        return sysUserMapper.selectOne(new QueryWrapper<SysUser>().
                eq("username", userName).eq("status", 0));
    }

    @Override
    public int updateUserPassword(String oldPassword, String newPassword, Integer userId) {
        SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().
                eq("username", "ry").eq("status", 0));
        sysUser.setPassword(passwordEncoder.encode(newPassword));
        return sysUserMapper.updateById(sysUser);
    }
}
