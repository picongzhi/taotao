package com.pcz.taotao.sso.controller;

import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.common.utils.CookieUtils;
import com.pcz.taotao.pojo.TbUser;
import com.pcz.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author picongzhi
 */
@Controller
public class UserController {
    @Value("${TT_TOKEN}")
    private String TT_TOKEN;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/check/{param}/{type}", method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult checkUserValid(@PathVariable("param") String param,
                                       @PathVariable("type") Integer type) {
        return userService.checkUserValid(param, type);
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult register(TbUser tbUser) {
        return userService.register(tbUser);
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult login(String username, String password,
                              HttpServletRequest request, HttpServletResponse response) {
        TaotaoResult taotaoResult = userService.login(username, password);
        CookieUtils.setCookie(request, response, TT_TOKEN, taotaoResult.getData().toString());
        return taotaoResult;
    }

    @RequestMapping(value = "/token/{token}", method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult getUserByToken(@PathVariable String token) {
        return userService.getUserByToken(token);
    }

    @RequestMapping(value = "/user/logout/{token}", method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult logout(@PathVariable String token) {
        return userService.logout(token);
    }
}
