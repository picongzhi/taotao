package com.pcz.taotao.solrj;

import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-*.xml"})
public class SolrCloudTest {
    @Test
    public void solrCloudAddDocumentTest() throws Exception {
        CloudSolrServer cloudSolrServer = new CloudSolrServer("localhost:2181,localhost:2182,localhost:2183");
        cloudSolrServer.setDefaultCollection("collection1");

        SolrInputDocument solrInputDocument = new SolrInputDocument();
        solrInputDocument.addField("id", "123456");
        solrInputDocument.addField("item_title", "title");
        solrInputDocument.addField("item_price", 100);

        cloudSolrServer.add(solrInputDocument);
        cloudSolrServer.commit();
    }
}
