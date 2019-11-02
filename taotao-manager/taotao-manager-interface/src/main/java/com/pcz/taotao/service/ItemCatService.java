package com.pcz.taotao.service;

import com.pcz.taotao.common.pojo.EasyUiTreeNode;

import java.util.List;

/**
 * @author picongzhi
 */
public interface ItemCatService {
    /**
     * 根据父节点id查询下面的分类
     *
     * @param parentId 父节点id
     * @return List<EasyUiTreeNode>
     */
    List<EasyUiTreeNode> getItemCatList(long parentId);
}
