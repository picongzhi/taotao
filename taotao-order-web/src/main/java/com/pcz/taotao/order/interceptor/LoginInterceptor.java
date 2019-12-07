package com.pcz.taotao.order.interceptor;

import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.common.utils.CookieUtils;
import com.pcz.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author picongzhi
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Value("${TT_TOKEN}")
    private String TT_TOKEN;
    @Value("${SSO_URL}")
    private String SSO_URL;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        if (StringUtils.isBlank(token)) {
            response.sendRedirect(SSO_URL + "/login?url=" + request.getRequestURL());
            return false;
        }

        TaotaoResult taotaoResult = userService.getUserByToken(token);
        if (taotaoResult.getStatus() != 200) {
            response.sendRedirect(SSO_URL + "/login?url=" + request.getRequestURL());
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
