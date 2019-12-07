package com.pcz.taotao.order.controller;

import com.pcz.taotao.common.utils.CookieUtils;
import com.pcz.taotao.common.utils.JsonUtils;
import com.pcz.taotao.pojo.TbItem;
import com.pcz.taotao.pojo.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author picongzhi
 */
@Controller
public class OrderCartController {
    @Value("${TT_CART_KEY}")
    private String TT_CART_KEY;

    @RequestMapping(value = "/order/order-cart", method = RequestMethod.GET)
    public String showOrderCart(HttpServletRequest request) {
        List<TbItem> cartList = getCartItemList(request);
        request.setAttribute("cartList", cartList);

        return "order-cart";
    }

    private List<TbItem> getCartItemList(HttpServletRequest request) {
        String value = CookieUtils.getCookieValue(request, TT_CART_KEY, true);
        if (StringUtils.isBlank(value)) {
            return new ArrayList<>();
        }

        TbUser tbUser = (TbUser) request.getAttribute("user");

        return JsonUtils.jsonToList(value, TbItem.class);
    }
}
