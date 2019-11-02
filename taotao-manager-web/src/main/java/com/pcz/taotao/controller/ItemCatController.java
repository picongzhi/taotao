package com.pcz.taotao.controller;

import com.pcz.taotao.common.pojo.EasyUiTreeNode;
import com.pcz.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author picongzhi
 */
@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUiTreeNode> getItemCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        return itemCatService.getItemCatList(parentId);
    }
}
