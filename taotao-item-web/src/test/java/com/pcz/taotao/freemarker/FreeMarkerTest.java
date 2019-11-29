package com.pcz.taotao.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class FreeMarkerTest {
    @Test
    public void freeMarkerTest() throws Exception {
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(
                new File("/Users/picongzhi/workspace/java/idea/taotao/taotao-item-web/src/main/webapp/WEB-INF/ftl"));
        configuration.setDefaultEncoding("utf-8");

        Template template = configuration.getTemplate("hello.ftl");
        Map<String, Object> data = new HashMap();
        data.put("hello", "hello world");
        Writer writer = new FileWriter(
                new File("/Users/picongzhi/workspace/java/idea/taotao/taotao-item-web/src/main/webapp/WEB-INF/ftl/out/hello.txt"));
        template.process(data, writer);

        writer.close();
    }

    @Test
    public void freeMarkerPojoTest() throws Exception {
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(
                new File("/Users/picongzhi/workspace/java/idea/taotao/taotao-item-web/src/main/webapp/WEB-INF/ftl"));
        configuration.setDefaultEncoding("utf-8");

        Template template = configuration.getTemplate("student.ftl");
        Map<String, Object> data = new HashMap();
        Student student = new Student(1, "picongzhi", 27, "Shanghai");
        data.put("student", student);

        Writer writer = new FileWriter(
                new File("/Users/picongzhi/workspace/java/idea/taotao/taotao-item-web/src/main/webapp/WEB-INF/ftl/out/student.html"));
        template.process(data, writer);

        writer.close();
    }
}
