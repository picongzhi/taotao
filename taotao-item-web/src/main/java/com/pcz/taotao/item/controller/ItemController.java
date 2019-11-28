package com.pcz.taotao.item.controller;

import com.pcz.taotao.item.pojo.Item;
import com.pcz.taotao.pojo.TbItem;
import com.pcz.taotao.pojo.TbItemDesc;
import com.pcz.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author picongzhi
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public String showItem(@PathVariable long itemId, Model model) {
        TbItem tbItem = itemService.getItemById(itemId);
        Item item = new Item(tbItem);
        TbItemDesc tbItemDesc = itemService.getItemDescById(itemId);

        model.addAttribute("item", item);
        model.addAttribute("itemDesc", tbItemDesc);

        return "item";
    }
}
