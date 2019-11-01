package com.pcz.taotao.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pcz.taotao.mapper.TbItemMapper;
import com.pcz.taotao.pojo.TbItem;
import com.pcz.taotao.pojo.TbItemExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class PageHelperTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageHelperTest.class);

    @Autowired
    private TbItemMapper tbItemMapper;

    @Test
    public void pageHelperTest() {
        PageHelper.startPage(1, 10);

        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> tbItemList = tbItemMapper.selectByExample(tbItemExample);
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItemList);

        LOGGER.info("list size: {}", pageInfo.getTotal());
        LOGGER.info("pages: {}", pageInfo.getPages());
        pageInfo.getList().forEach(tbItem -> LOGGER.info("item: {}", tbItem));
    }
}
