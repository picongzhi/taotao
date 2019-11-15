package com.pcz.taotao.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pcz.taotao.common.pojo.EasyUIDataGridResult;
import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.common.utils.JsonUtils;
import com.pcz.taotao.content.service.ContentService;
import com.pcz.taotao.jedis.JedisClient;
import com.pcz.taotao.mapper.TbContentMapper;
import com.pcz.taotao.pojo.TbContent;
import com.pcz.taotao.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author picongzhi
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Value("${index.content}")
    private String INDEX_CONTENT;

    @Autowired
    private TbContentMapper tbContentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public TaotaoResult addContent(TbContent tbContent) {
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        tbContentMapper.insert(tbContent);

        try {
            jedisClient.hdel(INDEX_CONTENT, String.valueOf(tbContent.getCategoryId()));
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        try {
            jedisClient.hdel(INDEX_CONTENT, String.valueOf(tbContent.getCategoryId()));
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        try {
            String content = jedisClient.hget(INDEX_CONTENT, String.valueOf(categoryId));
            if (StringUtils.isNotBlank(content)) {
                return JsonUtils.jsonToList(content, TbContent.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbContentExample tbContentExample = new TbContentExample();
        tbContentExample.createCriteria().andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContentList = tbContentMapper.selectByExample(tbContentExample);

        try {
            jedisClient.hset(INDEX_CONTENT, String.valueOf(categoryId), JsonUtils.objectToJson(tbContentList));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tbContentList;
    }
}
