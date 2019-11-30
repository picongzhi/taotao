package com.pcz.taotao.item.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author picongzhi
 */
@Controller
public class HtmlGenController {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @RequestMapping("/genHtml")
    @ResponseBody
    public String genHtml() throws Exception {
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Template template = configuration.getTemplate("hello.ftl");
        Map<String, Object> data = new HashMap<>();
        data.put("hello", "hello world");
        Writer writer = new FileWriter(
                new File("/Users/picongzhi/workspace/java/idea/taotao/taotao-item-web/src/main/webapp/WEB-INF/ftl/out/hello.html"));
        template.process(data, writer);
        writer.close();

        return "ok";
    }
}
