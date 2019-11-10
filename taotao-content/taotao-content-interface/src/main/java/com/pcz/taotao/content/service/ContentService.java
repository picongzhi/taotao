package com.pcz.taotao.content.service;

import com.pcz.taotao.common.pojo.EasyUIDataGridResult;
import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.pojo.TbContent;

import java.util.List;

/**
 * @author picongzhi
 */
public interface ContentService {
    /**
     * 新增内容
     *
     * @param tbContent 内容
     * @return TaotaoResult
     */
    TaotaoResult addContent(TbContent tbContent);

    /**
     * 根据分类id分页获取内容
     *
     * @param categoryId 分类id
     * @param page       页数
     * @param rows       每页大小
     * @return EasyUIDataGridResult
     */
    EasyUIDataGridResult pageableGetContentListByCategoryId(long categoryId, int page, int rows);

    /**
     * 更新内容
     *
     * @param tbContent 内容
     * @return TaotaoResult
     */
    TaotaoResult editContent(TbContent tbContent);

    /**
     * 根据ids删除内容
     *
     * @param ids ids
     * @return TaotaoResult
     */
    TaotaoResult deleteContentsByIds(List<Long> ids);

    /**
     * 根据分类id获取内容
     *
     * @param categoryId 分类id
     * @return List<TbContent>
     */
    List<TbContent> getContentsByCategoryId(long categoryId);
}
