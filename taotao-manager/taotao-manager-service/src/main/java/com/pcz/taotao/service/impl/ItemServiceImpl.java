package com.pcz.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pcz.taotao.common.pojo.EasyUIDataGridResult;
import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.common.utils.IDUtils;
import com.pcz.taotao.common.utils.JsonUtils;
import com.pcz.taotao.jedis.JedisClient;
import com.pcz.taotao.mapper.TbItemDescMapper;
import com.pcz.taotao.mapper.TbItemMapper;
import com.pcz.taotao.pojo.TbItem;
import com.pcz.taotao.pojo.TbItemDesc;
import com.pcz.taotao.pojo.TbItemExample;
import com.pcz.taotao.service.ItemService;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
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

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private ActiveMQTopic activeMQTopic;

    @Value("${ITEM_INFO}")
    private String ITEM_INFO;
    @Value("${ITEM_EXPIRE}")
    private Integer ITEM_EXPIRE;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public TbItem getItemById(long id) {
        String key = ITEM_INFO + ":" + id + ":BASE";

        try {
            String str = jedisClient.get(key);
            if (StringUtils.isNotBlank(str)) {
                return JsonUtils.jsonToPojo(str, TbItem.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItem tbItem = tbItemMapper.selectByPrimaryKey(id);
        try {
            jedisClient.set(key, JsonUtils.objectToJson(tbItem));
            jedisClient.expire(key, ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tbItem;
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

        jmsTemplate.send(activeMQTopic, session -> session.createTextMessage(Long.toString(tbItemId)));

        return TaotaoResult.ok();
    }

    @Override
    public TbItemDesc getItemDescById(long itemid) {
        String key = ITEM_INFO + ":" + itemid + ":DESC";

        try {
            String str = jedisClient.get(key);
            if (StringUtils.isNotBlank(str)) {
                return JsonUtils.jsonToPojo(str, TbItemDesc.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(itemid);
        try {
            jedisClient.set(key, JsonUtils.objectToJson(tbItemDesc));
            jedisClient.expire(key, ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tbItemDesc;
    }
}
