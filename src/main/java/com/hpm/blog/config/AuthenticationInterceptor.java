package com.hpm.blog.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.hpm.blog.annotation.LoginRequired;
import com.hpm.blog.model.User;
import com.hpm.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;


public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        // 有 @LoginRequired 注解，需要认证
        if (methodAnnotation != null) {
            // 执行认证
            String token = request.getHeader("token");  // 从 http 请求头中取出 token
            if (token == null) {
                throw new RuntimeException("无token，请重新登录");
            }
            int userId;
            try {
                userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));  // 获取 token 中的 user id
            } catch (JWTDecodeException e) {
                throw new RuntimeException("token无效，请重新登录");
            }
            User user = userService.findById(userId);
            if (user == null) {
                throw new RuntimeException("用户不存在，请重新登录");
            }
            // 验证 token
            try {
                JWTVerifier verifier =  JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    verifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("token无效，请重新登录");
                }
            } catch (UnsupportedEncodingException ignore) {}
            request.setAttribute("currentUser", user);
            return true;
        }
        return true;
    }

    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
