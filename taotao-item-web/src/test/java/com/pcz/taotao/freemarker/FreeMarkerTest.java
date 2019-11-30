package com.pcz.taotao.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;

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

    @Test
    public void freeMarkerListTest() throws Exception {
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(
                new File("/Users/picongzhi/workspace/java/idea/taotao/taotao-item-web/src/main/webapp/WEB-INF/ftl"));
        configuration.setDefaultEncoding("utf-8");

        Template template = configuration.getTemplate("students.ftl");
        Map<String, Object> data = new HashMap();
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "picongzhi", 26, "Shanghai"));
        studentList.add(new Student(2, "picongzhi", 27, "Shanghai"));
        studentList.add(new Student(3, "picongzhi", 28, "Shanghai"));

        data.put("students", studentList);

        Writer writer = new FileWriter(
                new File("/Users/picongzhi/workspace/java/idea/taotao/taotao-item-web/src/main/webapp/WEB-INF/ftl/out/students.html"));
        template.process(data, writer);

        writer.close();
    }

    @Test
    public void freeMarkerDateTest() throws Exception {
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(
                new File("/Users/picongzhi/workspace/java/idea/taotao/taotao-item-web/src/main/webapp/WEB-INF/ftl"));
        configuration.setDefaultEncoding("utf-8");

        Template template = configuration.getTemplate("date.ftl");
        Map<String, Object> data = new HashMap();
        data.put("date", new Date());

        Writer writer = new FileWriter(
                new File("/Users/picongzhi/workspace/java/idea/taotao/taotao-item-web/src/main/webapp/WEB-INF/ftl/out/date.html"));
        template.process(data, writer);

        writer.close();
    }

    @Test
    public void freeMarkerNullTest() throws Exception {
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(
                new File("/Users/picongzhi/workspace/java/idea/taotao/taotao-item-web/src/main/webapp/WEB-INF/ftl"));
        configuration.setDefaultEncoding("utf-8");

        Template template = configuration.getTemplate("null.ftl");
        Map<String, Object> data = new HashMap();

        Writer writer = new FileWriter(
                new File("/Users/picongzhi/workspace/java/idea/taotao/taotao-item-web/src/main/webapp/WEB-INF/ftl/out/null.html"));
        template.process(data, writer);

        writer.close();
    }
}
