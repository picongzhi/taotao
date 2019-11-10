package com.pcz.taotao.controller;

import com.pcz.taotao.common.pojo.EasyUiTreeNode;
import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.content.service.ContentCategoryService;
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
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUiTreeNode> getContentCategoryList(@RequestParam(value = "id", defaultValue = "0") long parentId) {
        return contentCategoryService.getContentCategoryList(parentId);
    }

    @RequestMapping("/content/category/create")
    @ResponseBody
    public TaotaoResult addContentCategory(long parentId, String name) {
        return contentCategoryService.addContentCategory(parentId, name);
    }

    @RequestMapping("/content/category/update")
    @ResponseBody
    public TaotaoResult updateContentCategory(long id, String name) {
        return contentCategoryService.updateContentCategory(id, name);
    }

    @RequestMapping("/content/category/delete")
    @ResponseBody
    public TaotaoResult deleteContentCategory(long id) {
        return contentCategoryService.deleteContentCategory(id);
    }
}
