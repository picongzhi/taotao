package com.pcz.taotao.search.service;

import com.pcz.taotao.common.pojo.SearchResult;
import com.pcz.taotao.common.pojo.TaotaoResult;

/**
 * @author picongzhi
 */
public interface SearchItemService {
    /**
     * 导入商品数据到索引库
     *
     * @return TaotaoResult
     */
    TaotaoResult importItemsToIndex();

    /**
     * 搜索商品
     *
     * @param query 关键字
     * @param page  页数
     * @param rows  每页大小
     * @return SearchResult
     */
    SearchResult search(String query, int page, int rows) throws Exception;
}
