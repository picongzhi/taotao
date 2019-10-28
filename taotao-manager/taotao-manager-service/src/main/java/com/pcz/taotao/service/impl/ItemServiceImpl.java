package com.pcz.taotao.service.impl;

import com.pcz.taotao.mapper.TbItemMapper;
import com.pcz.taotao.pojo.TbItem;
import com.pcz.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author picongzhi
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public TbItem getItemById(long id) {
        return tbItemMapper.selectByPrimaryKey(id);
    }
}
