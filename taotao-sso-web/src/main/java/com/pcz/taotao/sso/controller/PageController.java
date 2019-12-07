package com.pcz.taotao.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author picongzhi
 */
@Controller
public class PageController {
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "url", required = false) String url,
                        Model model) {
        if (StringUtils.isNotBlank(url)) {
            model.addAttribute("redirect", url);
        }

        return "login";
    }
}
