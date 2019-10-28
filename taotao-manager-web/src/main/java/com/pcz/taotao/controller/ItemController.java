package com.pcz.taotao.controller;

import com.pcz.taotao.pojo.TbItem;
import com.pcz.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author picongzhi
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{id}")
    @ResponseBody
    public TbItem getItemById(@PathVariable long id) {
        return itemService.getItemById(id);
    }
}
