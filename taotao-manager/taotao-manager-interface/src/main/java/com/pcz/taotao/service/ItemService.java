package com.pcz.taotao.service;

import com.pcz.taotao.pojo.TbItem;

/**
 * @author picongzhi
 */
public interface ItemService {
    /**
     * 根据id获取item
     *
     * @param id id
     * @return TbItem
     */
    TbItem getItemById(long id);
}
