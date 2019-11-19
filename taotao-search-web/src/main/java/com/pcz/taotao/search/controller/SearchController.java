package com.pcz.taotao.search.controller;

import com.pcz.taotao.common.pojo.SearchResult;
import com.pcz.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author picongzhi
 */
@Controller
public class SearchController {
    @Value("${SEARCH_RESULT_ROWS}")
    private Integer searchResultRows;

    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping("/search")
    public String search(@RequestParam("q") String query,
                         @RequestParam(defaultValue = "1") Integer page,
                         Model model) throws Exception {
        query = new String(query.getBytes("iso8859-1"), "utf-8");
        SearchResult searchResult = searchItemService.search(query, page, searchResultRows);
        model.addAttribute("query", query);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", searchResult.getTotalPages());
        model.addAttribute("itemList", searchResult.getItemList());

        return "search";
    }
}
