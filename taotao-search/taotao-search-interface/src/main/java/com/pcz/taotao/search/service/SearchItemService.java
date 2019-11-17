package com.pcz.taotao.search.service;

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
}
