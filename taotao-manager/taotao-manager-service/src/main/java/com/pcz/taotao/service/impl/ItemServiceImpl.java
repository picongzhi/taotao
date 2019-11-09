package com.pcz.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pcz.taotao.common.pojo.EasyUIDataGridResult;
import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.common.utils.IDUtils;
import com.pcz.taotao.mapper.TbItemDescMapper;
import com.pcz.taotao.mapper.TbItemMapper;
import com.pcz.taotao.pojo.TbItem;
import com.pcz.taotao.pojo.TbItemDesc;
import com.pcz.taotao.pojo.TbItemExample;
import com.pcz.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author picongzhi
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public TbItem getItemById(long id) {
        return tbItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        PageHelper.startPage(page, rows);
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> tbItemList = tbItemMapper.selectByExample(tbItemExample);
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItemList);

        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(pageInfo.getList());
        result.setTotal(pageInfo.getTotal());

        return result;
    }

    @Override
    public TaotaoResult addItem(TbItem tbItem, String description) {
        long tbItemId = IDUtils.genItemId();
        tbItem.setId(tbItemId);
        // 商品状态，1-正常，2-下架，3-删除
        tbItem.setStatus((byte) 1);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        tbItemMapper.insert(tbItem);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(tbItemId);
        tbItemDesc.setItemDesc(description);
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(new Date());
        tbItemDescMapper.insert(tbItemDesc);

        return TaotaoResult.ok();
    }
}
