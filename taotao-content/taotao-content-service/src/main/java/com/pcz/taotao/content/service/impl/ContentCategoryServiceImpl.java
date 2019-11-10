package com.pcz.taotao.content.service.impl;

import com.pcz.taotao.common.pojo.EasyUiTreeNode;
import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.content.service.ContentCategoryService;
import com.pcz.taotao.mapper.TbContentCategoryMapper;
import com.pcz.taotao.pojo.TbContentCategory;
import com.pcz.taotao.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author picongzhi
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<EasyUiTreeNode> getContentCategoryList(long parentId) {
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        tbContentCategoryExample.createCriteria().andParentIdEqualTo(parentId);

        List<TbContentCategory> tbContentCategoryList = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        List<EasyUiTreeNode> easyUiTreeNodeList = new ArrayList<>();
        tbContentCategoryList.forEach(tbContentCategory -> {
            EasyUiTreeNode easyUiTreeNode = new EasyUiTreeNode();
            easyUiTreeNode.setId(tbContentCategory.getId());
            easyUiTreeNode.setText(tbContentCategory.getName());
            easyUiTreeNode.setState(tbContentCategory.getIsParent() ? "closed" : "open");

            easyUiTreeNodeList.add(easyUiTreeNode);
        });

        return easyUiTreeNodeList;
    }

    @Override
    public TaotaoResult addContentCategory(long parentId, String name) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setName(name);
        // 状态，1-正常，2-删除
        tbContentCategory.setStatus(1);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setUpdated(new Date());
        tbContentCategory.setIsParent(false);
        tbContentCategoryMapper.insert(tbContentCategory);

        TbContentCategory parentTbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parentTbContentCategory.getIsParent()) {
            parentTbContentCategory.setIsParent(true);
            tbContentCategoryMapper.updateByPrimaryKey(parentTbContentCategory);
        }

        return TaotaoResult.ok(tbContentCategory);
    }

    @Override
    public TaotaoResult updateContentCategory(long id, String name) {
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
        tbContentCategory.setName(name);
        tbContentCategory.setUpdated(new Date());
        tbContentCategoryMapper.updateByPrimaryKey(tbContentCategory);

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteContentCategory(long id) {
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
        if (tbContentCategory.getIsParent()) {
            return TaotaoResult.build(403, "只允许删除叶子节点的服务分类");
        }

        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        tbContentCategoryExample.createCriteria().andParentIdEqualTo(tbContentCategory.getParentId());
        List<TbContentCategory> tbContentCategoryList = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        if (tbContentCategoryList.size() == 1) {
            TbContentCategory parentTbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
            parentTbContentCategory.setIsParent(false);
            tbContentCategoryMapper.updateByPrimaryKey(parentTbContentCategory);
        }
        tbContentCategoryMapper.deleteByPrimaryKey(id);

        return TaotaoResult.ok();
    }
}
