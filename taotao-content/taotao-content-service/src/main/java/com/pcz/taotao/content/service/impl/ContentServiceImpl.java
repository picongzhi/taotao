package com.pcz.taotao.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pcz.taotao.common.pojo.EasyUIDataGridResult;
import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.content.service.ContentService;
import com.pcz.taotao.mapper.TbContentMapper;
import com.pcz.taotao.pojo.TbContent;
import com.pcz.taotao.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author picongzhi
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper tbContentMapper;

    @Override
    public TaotaoResult addContent(TbContent tbContent) {
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        tbContentMapper.insert(tbContent);

        return TaotaoResult.ok();
    }

    @Override
    public EasyUIDataGridResult pageableGetContentListByCategoryId(long categoryId, int page, int rows) {
        PageHelper.startPage(page, rows);
        TbContentExample tbContentExample = new TbContentExample();
        tbContentExample.createCriteria().andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContentList = tbContentMapper.selectByExample(tbContentExample);
        PageInfo<TbContent> tbContentPageInfo = new PageInfo<>(tbContentList);

        EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
        easyUIDataGridResult.setTotal(tbContentPageInfo.getTotal());
        easyUIDataGridResult.setRows(tbContentPageInfo.getList());

        return easyUIDataGridResult;
    }

    @Override
    public TaotaoResult editContent(TbContent tbContent) {
        tbContent.setUpdated(new Date());
        tbContentMapper.updateByPrimaryKey(tbContent);

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteContentsByIds(List<Long> ids) {
        TbContentExample tbContentExample = new TbContentExample();
        tbContentExample.createCriteria().andIdIn(ids);
        tbContentMapper.deleteByExample(tbContentExample);

        return TaotaoResult.ok();
    }

    @Override
    public List<TbContent> getContentsByCategoryId(long categoryId) {
        TbContentExample tbContentExample = new TbContentExample();
        tbContentExample.createCriteria().andCategoryIdEqualTo(categoryId);

        return tbContentMapper.selectByExample(tbContentExample);
    }
}
