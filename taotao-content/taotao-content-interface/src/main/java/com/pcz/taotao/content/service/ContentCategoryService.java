package com.pcz.taotao.content.service;

import com.pcz.taotao.common.pojo.EasyUiTreeNode;
import com.pcz.taotao.common.pojo.TaotaoResult;

import java.util.List;

/**
 * @author picongzhi
 */
public interface ContentCategoryService {
    /**
     * 根据parentId获取内容分类
     *
     * @param parentId 父类id
     * @return List<EasyUiTreeNode>
     */
    List<EasyUiTreeNode> getContentCategoryList(long parentId);

    /**
     * 添加内容分类
     *
     * @param parentId 父类id
     * @param name     分类名
     * @return TaotaoResult
     */
    TaotaoResult addContentCategory(long parentId, String name);

    /**
     * 更新内容分类
     *
     * @param id   id
     * @param name 分类名
     * @return TaotaoResult
     */
    TaotaoResult updateContentCategory(long id, String name);

    /**
     * 删除内容分类，只允许删除叶子节点
     *
     * @param id id
     * @return TaotaoResult
     */
    TaotaoResult deleteContentCategory(long id);
}
