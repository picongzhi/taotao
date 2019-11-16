package com.pcz.taotao.search.mapper;

import com.pcz.taotao.common.pojo.SearchItem;

import java.util.List;

/**
 * @author picongzhi
 */
public interface SearchItemMapper {
    /**
     * 获取搜索商品
     *
     * @return List<SearchItem>
     */
    List<SearchItem> getItemList();
}
