package com.pcz.taotao.service.impl;

import com.pcz.taotao.search.service.SearchItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-*.xml"})
public class SearchItemServiceImplTest {
    @Autowired
    private SearchItemService searchItemService;

    @Test
    public void importItemsToIndexTest() {
        searchItemService.importItemsToIndex();
    }
}
