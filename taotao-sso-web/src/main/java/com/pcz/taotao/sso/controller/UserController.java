package com.pcz.taotao.sso.controller;

import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.pojo.TbUser;
import com.pcz.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult register(TbUser tbUser) {
        return userService.register(tbUser);
    }
}
