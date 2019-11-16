package com.pcz.taotao.solrj;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

public class SolrJTest {
    @Test
    public void addDocumentTest() throws Exception {
        SolrServer solrServer = new HttpSolrServer("http://localhost:8983/solr/collection1");

        SolrInputDocument solrInputDocument = new SolrInputDocument();
        solrInputDocument.addField("id", "123456");
        solrInputDocument.addField("item_title", "title");
        solrInputDocument.addField("item_price", 1000);

        solrServer.add(solrInputDocument);
        solrServer.commit();
    }

    @Test
    public void deleteDocumentByIdTest() throws Exception {
        SolrServer solrServer = new HttpSolrServer("http://localhost:8983/solr/collection1");
        solrServer.deleteById("123456");
        solrServer.commit();
    }

    @Test
    public void deleteByQueryTest() throws Exception {
        SolrServer solrServer = new HttpSolrServer("http://localhost:8983/solr/collection1");
        solrServer.deleteByQuery("id:123456");
        solrServer.commit();
    }
}
