package com.pcz.taotao.sso.controller;

import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author picongzhi
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/check/{param}/{type}", method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult checkUserValid(@PathVariable("param") String param,
                                       @PathVariable("type") Integer type) {
        return userService.checkUserValid(param, type);
    }
}
