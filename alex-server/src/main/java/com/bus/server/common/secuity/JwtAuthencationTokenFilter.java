package com.bus.server.common.secuity;

import com.bus.server.common.utils.JwtTokenUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;


/**
 * @ClassName: JwtAuthencationTokenFilter
 * @Description:
 * @Author: hlh
 * @Date: 2021-12-26 1:37
 */
public class JwtAuthencationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(tokenHeader);
        //存在token
        if (null != authHeader && authHeader.startsWith(tokenHead)) {
            String authToken = authHeader.substring(tokenHead.length());
            String userName = jwtTokenUtil.getUserNameFromToken(authToken);
            //token存在 但是未登录
            if (null != userName && null == SecurityContextHolder.getContext().getAuthentication()) {
                //登录
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    //更新security登录用户对象
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Swagger 配置属性
     *
     * @author
     */
    @ConfigurationProperties(prefix = "swagger")
    @Data
    public static class SwaggerProperties {

        /**
         * 标题
         */
        @NotEmpty(message = "标题不能为空")
        private String title;
        /**
         * 描述
         */
        @NotEmpty(message = "描述不能为空")
        private String description;
        /**
         * 作者
         */
        @NotEmpty(message = "作者不能为空")
        private String author;
        /**
         * 版本
         */
        @NotEmpty(message = "版本不能为空")
        private String version;
        /**
         * 扫描的包
         */
        @NotEmpty(message = "扫描的 package 不能为空")
        private String basePackage;

    }
}
