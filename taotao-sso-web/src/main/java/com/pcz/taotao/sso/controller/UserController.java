package com.pcz.taotao.sso.controller;

import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.common.utils.CookieUtils;
import com.pcz.taotao.common.utils.JsonUtils;
import com.pcz.taotao.pojo.TbUser;
import com.pcz.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
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
        if (taotaoResult.getData() != null) {
            CookieUtils.setCookie(request, response, TT_TOKEN, taotaoResult.getData().toString());
        }

        return taotaoResult;
    }

    @RequestMapping(
            value = "/user/token/{token}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getUserByToken(@PathVariable String token, @RequestParam(value = "callback", required = false) String callback) {
        TaotaoResult taotaoResult = userService.getUserByToken(token);
        if (StringUtils.isNotBlank(callback)) {
            return callback + "(" + JsonUtils.objectToJson(taotaoResult) + ")";
        }

        return JsonUtils.objectToJson(taotaoResult);
    }

    @RequestMapping(value = "/user/logout/{token}", method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult logout(@PathVariable String token) {
        return userService.logout(token);
    }
}
