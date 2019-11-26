package com.pcz.taotao.search.listener;

import com.pcz.taotao.common.pojo.SearchItem;
import com.pcz.taotao.search.mapper.SearchItemMapper;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author picongzhi
 */
public class ItemAddMessageListener implements MessageListener {
    @Autowired
    private SearchItemMapper searchItemMapper;
    @Autowired
    private SolrServer solrServer;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            long itemId = Long.parseLong(textMessage.getText());
            Thread.sleep(1000);

            SearchItem searchItem = searchItemMapper.getItemById(itemId);
            SolrInputDocument solrInputDocument = new SolrInputDocument();
            solrInputDocument.addField("id", searchItem.getId());
            solrInputDocument.addField("item_title", searchItem.getTitle());
            solrInputDocument.addField("item_sell_point", searchItem.getSellPoint());
            solrInputDocument.addField("item_price", searchItem.getPrice());
            solrInputDocument.addField("item_image", searchItem.getImage());
            solrInputDocument.addField("item_category_name", searchItem.getCategoryName());

            solrServer.add(solrInputDocument);
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
