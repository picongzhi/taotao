package com.pcz.taotao.dao;

import com.pcz.taotao.common.pojo.SearchResult;
import com.pcz.taotao.search.dao.SearchDao;
import org.apache.solr.client.solrj.SolrQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-*.xml"})
public class SearchDaoTest {
    @Autowired
    private SearchDao searchDao;

    @Test
    public void searchTest() throws Exception {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("手机");
        solrQuery.setStart(0);
        solrQuery.setRows(100);
        solrQuery.set("df", "item_keywords");

        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em>");
        solrQuery.setHighlightSimplePost("</em>");

        SearchResult searchResult = searchDao.search(solrQuery);
        System.out.println(searchResult);
    }
}
