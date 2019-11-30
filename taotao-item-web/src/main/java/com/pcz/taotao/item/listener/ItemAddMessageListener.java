package com.pcz.taotao.item.listener;

import com.pcz.taotao.item.pojo.Item;
import com.pcz.taotao.pojo.TbItem;
import com.pcz.taotao.pojo.TbItemDesc;
import com.pcz.taotao.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author picongzhi
 */
public class ItemAddMessageListener implements MessageListener {
    @Autowired
    private ItemService itemService;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${HTML_OUT_PATH}")
    private String HTML_OUT_PATH;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            long itemId = Long.parseLong(textMessage.getText());
            Thread.sleep(1000);

            TbItem tbItem = itemService.getItemById(itemId);
            Item item = new Item(tbItem);
            TbItemDesc tbItemDesc = itemService.getItemDescById(itemId);

            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            Template template = configuration.getTemplate("item.ftl");
            Map<String, Object> data = new HashMap<>();
            data.put("item", item);
            data.put("itemDesc", tbItemDesc);

            Writer writer = new FileWriter(new File(HTML_OUT_PATH + itemId + ".html"));
            template.process(data, writer);

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
