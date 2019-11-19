package com.pcz.taotao.search.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author picongzhi
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "请求异常");
        modelAndView.setViewName("error/exception");

        ex.printStackTrace();
        LOGGER.error("请求异常", ex);

        return modelAndView;
    }
}
