package com.pcz.taotao.controller;

import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author picongzhi
 */
@Controller
public class IndexManagerController {
    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping(value = "/index/import", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult importIndex() {
        return searchItemService.importItemsToIndex();
    }
}
