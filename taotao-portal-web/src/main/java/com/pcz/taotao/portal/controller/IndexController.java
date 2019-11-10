package com.pcz.taotao.portal.controller;

import com.pcz.taotao.common.utils.JsonUtils;
import com.pcz.taotao.content.service.ContentService;
import com.pcz.taotao.pojo.TbContent;
import com.pcz.taotao.portal.pojo.AD1Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Value("${AD1_CATEGORY_ID}")
    private long AD1_CATEGORY_ID;

    @Value("${AD1_WIDTH}")
    private int AD1_WIDTH;

    @Value("${AD1_WIDTH_B}")
    private int AD1_WIDTH_B;

    @Value("${AD1_HEIGHT}")
    private int AD1_HEIGHT;

    @Value("${AD1_HEIGHT_B}")
    private int AD1_HEIGHT_B;

    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model) {
        List<TbContent> tbContentList = contentService.getContentsByCategoryId(AD1_CATEGORY_ID);
        List<AD1Node> ad1NodeList = new ArrayList<>();
        tbContentList.forEach(tbContent -> {
            AD1Node ad1Node = new AD1Node();
            ad1Node.setAlt(tbContent.getTitle());
            ad1Node.setSrc(tbContent.getPic());
            ad1Node.setSrcB(tbContent.getPic2());
            ad1Node.setHref(tbContent.getUrl());
            ad1Node.setWidth(AD1_WIDTH);
            ad1Node.setWidthB(AD1_WIDTH_B);
            ad1Node.setHeight(AD1_HEIGHT);
            ad1Node.setWidthB(AD1_HEIGHT_B);

            ad1NodeList.add(ad1Node);
        });

        String ad1Json = JsonUtils.objectToJson(ad1NodeList);
        model.addAttribute("ad1", ad1Json);

        return "index";
    }
}
