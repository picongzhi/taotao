package com.pcz.taotao.search.service.impl;

import com.pcz.taotao.common.pojo.SearchItem;
import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.search.mapper.SearchItemMapper;
import com.pcz.taotao.search.service.SearchItemService;
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
}
