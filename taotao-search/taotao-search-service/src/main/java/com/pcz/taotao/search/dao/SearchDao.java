package com.pcz.taotao.search.dao;

import com.pcz.taotao.common.pojo.SearchItem;
import com.pcz.taotao.common.pojo.SearchResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


/**
 * @author picongzhi
 */
@Repository
public class SearchDao {
    @Autowired
    private SolrServer solrServer;

    public SearchResult search(SolrQuery solrQuery) throws Exception {
        QueryResponse queryResponse = solrServer.query(solrQuery);
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        long numFound = solrDocumentList.getNumFound();

        SearchResult searchResult = new SearchResult();
        searchResult.setRecordCount(numFound);
        List<SearchItem> searchItemList = new ArrayList<>();
        for (SolrDocument solrDocument : solrDocumentList) {
            SearchItem searchItem = new SearchItem();
            searchItem.setId((String) solrDocument.get("id"));
            searchItem.setSellPoint((String) solrDocument.get("item_sell_point"));
            searchItem.setPrice(((List<Long>) solrDocument.get("item_price")).get(0));

            String image = (String) solrDocument.get("item_image");
            if (StringUtils.isNotBlank(image)) {
                image = image.split(",")[0];
            }
            searchItem.setImage(image);
            searchItem.setCategoryName((String) solrDocument.get("item_category_name"));

            List<String> highlightList = queryResponse.getHighlighting().
                    get(solrDocument.get("id")).
                    get("item_title");
            String title = (String) solrDocument.get("item_title");
            if (highlightList != null && highlightList.size() > 0) {
                title = highlightList.get(0);
            }
            searchItem.setTitle(title);

            searchItemList.add(searchItem);
        }

        searchResult.setItemList(searchItemList);

        return searchResult;
    }
}
