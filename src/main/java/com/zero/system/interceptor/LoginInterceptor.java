package com.zero.system.interceptor;

import com.zero.system.util.Const;
import com.zero.system.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname LoginInterceptor
 * @Description 登录拦截器
 * @Date 2019/7/17 13:51
 * @Created by WDD
 */
//@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String contextPath = request.getRequestURI();
        String token = getCookie(request, "token");
        String userId = getCookie(request, "userId");
        String rid = redisUtil.get(userId).toString();
        if (!StringUtils.isEmpty(rid)) {
            if (!token.equals(rid)) {
                response.sendRedirect(request.getContextPath() + "/manager/login");
                return false;
            }
        }
        if(!StringUtils.isEmpty(request.getSession().getAttribute(Const.ADMIN))){
            return true;
        }

//        String userId= getCookie(request,"userId");
//        response.sendRedirect(request.getContextPath() + "/manager/login");
        return false;
    }

    private String getCookie(HttpServletRequest request, String cookieName) {
        // TODO Auto-generated method stub
        Cookie[] cookies=request.getCookies();
        //如果客户端没有cookie，就会返回null
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookieName.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


}
