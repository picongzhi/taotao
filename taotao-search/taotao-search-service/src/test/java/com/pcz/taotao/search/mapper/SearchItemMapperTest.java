package com.pcz.taotao.search.mapper;

import com.github.pagehelper.PageHelper;
import com.pcz.taotao.common.pojo.SearchItem;
import com.pcz.taotao.mapper.TbItemMapper;
import com.pcz.taotao.pojo.TbItem;
import com.pcz.taotao.pojo.TbItemExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-*.xml"})
public class SearchItemMapperTest {
    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private SearchItemMapper searchItemMapper;

    @Test
    public void getSearchItemListTest() {
        List<SearchItem> searchItemList = searchItemMapper.getItemList();
        System.out.println(searchItemList);
    }

    @Test
    public void getItemListTest() {
        PageHelper.startPage(1, 10);
        List<TbItem> tbItemList = tbItemMapper.selectByExample(new TbItemExample());
        System.out.println(tbItemList);
    }

    @Test
    public void test() {
        SearchItem searchItem = searchItemMapper.getItemById(988634L);
        System.out.println(searchItem);
    }
}
