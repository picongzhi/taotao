package com.pcz.taotao.search.service.impl;

import com.pcz.taotao.common.pojo.SearchItem;
import com.pcz.taotao.common.pojo.SearchResult;
import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.search.dao.SearchDao;
import com.pcz.taotao.search.mapper.SearchItemMapper;
import com.pcz.taotao.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author picongzhi
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private SolrServer solrServer;

    @Autowired
    private SearchDao searchDao;

    @Override
    public TaotaoResult importItemsToIndex() {
        List<SearchItem> searchItemList = searchItemMapper.getItemList();

        try {
            for (SearchItem searchItem : searchItemList) {
                SolrInputDocument solrInputDocument = new SolrInputDocument();
                solrInputDocument.addField("id", searchItem.getId());
                solrInputDocument.addField("item_title", searchItem.getTitle());
                solrInputDocument.addField("item_sell_point", searchItem.getSellPoint());
                solrInputDocument.addField("item_price", searchItem.getPrice());
                solrInputDocument.addField("item_image", searchItem.getImage());
                solrInputDocument.addField("item_category_name", searchItem.getCategoryName());

                solrServer.add(solrInputDocument);
            }

            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, e.getMessage());
        }

        return TaotaoResult.ok();
    }

    @Override
    public SearchResult search(String query, int page, int rows) throws Exception {
        page = page < 1 ? 1 : page;
        rows = rows < 1 ? 1 : rows;

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(query);
        solrQuery.setStart((page - 1) * rows);
        solrQuery.setRows(rows);
        solrQuery.set("df", "item_title");

        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em>");
        solrQuery.setHighlightSimplePost("</em>");

        SearchResult searchResult = searchDao.search(solrQuery);
        int totalPage = (int) Math.floorDiv(searchResult.getRecordCount(), (long) rows) + 1;
        searchResult.setTotalPages(totalPage);

        return searchResult;
    }
}
