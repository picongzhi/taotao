package com.pcz.taotao.service;

import com.pcz.taotao.common.pojo.EasyUIDataGridResult;
import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.pojo.TbItem;
import com.pcz.taotao.pojo.TbItemDesc;

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

    /**
     * 添加商品
     *
     * @param tbItem      商品信息
     * @param description 描述
     * @return TaotaoResult
     */
    TaotaoResult addItem(TbItem tbItem, String description);

    /**
     * 根据id获取商品描述信息
     *
     * @param itemid 商品id
     * @return TbItemDesc
     */
    TbItemDesc getItemDescById(long itemid);
}
