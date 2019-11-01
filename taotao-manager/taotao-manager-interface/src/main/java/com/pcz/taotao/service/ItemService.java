package com.pcz.taotao.service;

import com.pcz.taotao.common.pojo.EasyUIDataGridResult;
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

    /**
     * 分页获取item list
     *
     * @param page 页数
     * @param rows 每页大小
     * @return EasyUIDataGridResult
     */
    EasyUIDataGridResult getItemList(int page, int rows);
}
