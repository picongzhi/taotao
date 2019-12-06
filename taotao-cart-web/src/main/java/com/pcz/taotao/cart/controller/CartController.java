package com.pcz.taotao.cart.controller;

import com.pcz.taotao.common.utils.CookieUtils;
import com.pcz.taotao.common.utils.JsonUtils;
import com.pcz.taotao.pojo.TbItem;
import com.pcz.taotao.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author picongzhi
 */
@Controller
public class CartController {
    @Value("${TT_CART_KEY}")
    private String TT_CART_KEY;
    @Value("${TT_CART_EXPIRE}")
    private Integer TT_CART_EXPIRE;

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/cart/add/{itemId}", method = RequestMethod.GET)
    public String addItemCart(@PathVariable("itemId") Long itemId,
                              @RequestParam(value = "num", defaultValue = "1") Integer num,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        List<TbItem> cartItemList = getCartItemList(request);
        boolean isInCart = false;
        for (TbItem cartItem : cartItemList) {
            if (cartItem.getId().equals(itemId)) {
                cartItem.setNum(cartItem.getNum() + num);
                isInCart = true;
                break;
            }
        }

        if (!isInCart) {
            TbItem tbItem = itemService.getItemById(itemId);
            tbItem.setNum(num);
            if (StringUtils.isNotBlank(tbItem.getImage())) {
                String[] images = tbItem.getImage().split(",");
                tbItem.setImage(images[0]);
            }

            cartItemList.add(tbItem);
        }

        CookieUtils.setCookie(request, response, TT_CART_KEY, JsonUtils.objectToJson(cartItemList),
                TT_CART_EXPIRE, true);

        return "cartSuccess";
    }

    private List<TbItem> getCartItemList(HttpServletRequest request) {
        String value = CookieUtils.getCookieValue(request, TT_CART_KEY, true);
        if (StringUtils.isBlank(value)) {
            return new ArrayList<>();
        }

        return JsonUtils.jsonToList(value, TbItem.class);
    }
}
