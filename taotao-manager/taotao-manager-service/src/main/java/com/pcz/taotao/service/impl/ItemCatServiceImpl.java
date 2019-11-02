package com.pcz.taotao.service.impl;

import com.pcz.taotao.common.pojo.EasyUiTreeNode;
import com.pcz.taotao.mapper.TbItemCatMapper;
import com.pcz.taotao.pojo.TbItemCat;
import com.pcz.taotao.pojo.TbItemCatExample;
import com.pcz.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author picongzhi
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<EasyUiTreeNode> getItemCatList(long parentId) {
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        tbItemCatExample.createCriteria().andParentIdEqualTo(parentId);

        List<TbItemCat> tbItemCatList = tbItemCatMapper.selectByExample(tbItemCatExample);
        List<EasyUiTreeNode> easyUiTreeNodeList = new ArrayList<>();
        tbItemCatList.forEach(tbItemCat -> {
            EasyUiTreeNode easyUiTreeNode = new EasyUiTreeNode();
            easyUiTreeNode.setId(tbItemCat.getId());
            easyUiTreeNode.setText(tbItemCat.getName());
            easyUiTreeNode.setState(tbItemCat.getIsParent() ? "closed" : "open");

            easyUiTreeNodeList.add(easyUiTreeNode);
        });

        return easyUiTreeNodeList;
    }
}
