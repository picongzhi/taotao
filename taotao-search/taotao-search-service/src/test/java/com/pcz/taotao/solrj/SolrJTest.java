package com.pcz.taotao.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-*.xml"})
public class SolrJTest {
    @Autowired
    private SolrServer solrServer;

    @Test
    public void addDocumentTest() throws Exception {
        SolrInputDocument solrInputDocument = new SolrInputDocument();
        solrInputDocument.addField("id", "123456");
        solrInputDocument.addField("item_title", "title");
        solrInputDocument.addField("item_price", 1000);

        solrServer.add(solrInputDocument);
        solrServer.commit();
    }

    @Test
    public void deleteDocumentByIdTest() throws Exception {
        solrServer.deleteById("123456");
        solrServer.commit();
    }

    @Test
    public void deleteByQueryTest() throws Exception {
        solrServer.deleteByQuery("id:123456");
        solrServer.commit();
    }

    @Test
    public void searchDocumentTest() throws Exception {
        SolrQuery solrQuery = new SolrQuery();
//        solrQuery.set("q", "*:*");
//        solrQuery.setQuery("*:*");
        solrQuery.setQuery("手机");
        solrQuery.setStart(0);
        solrQuery.setRows(100);
        solrQuery.set("df", "item_keywords");

        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em>");
        solrQuery.setHighlightSimplePost("</em>");

        QueryResponse queryResponse = solrServer.query(solrQuery);
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        System.out.println("Total results: " + solrDocumentList.getNumFound());
        for (SolrDocument solrDocument : solrDocumentList) {
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("item_title"));
            System.out.println(solrDocument.get("item_sell_point"));
            System.out.println(solrDocument.get("item_price"));
            System.out.println(solrDocument.get("item_image"));
            System.out.println(solrDocument.get("item_category_name"));

            List<String> highlightList = queryResponse.getHighlighting().
                    get(solrDocument.get("id")).
                    get("item_title");
            if (highlightList != null && highlightList.size() > 0) {
                System.out.println(highlightList.get(0));
            } else {
                System.out.println(solrDocument.get("item_title"));
            }

            System.out.println("=================");
        }

        queryResponse.getHighlighting();
    }
}
