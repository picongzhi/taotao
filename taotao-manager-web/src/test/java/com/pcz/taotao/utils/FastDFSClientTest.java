package com.pcz.taotao.utils;

import org.junit.Assert;
import org.junit.Test;

public class FastDFSClientTest {
    @Test
    public void testInit() throws Exception {
        String conf = "classpath:fdfs.conf";
        FastDFSClient fastDFSClient = new FastDFSClient(conf);
        Assert.assertNotNull(fastDFSClient);
    }

    @Test
    public void testUpload() throws Exception {
        String conf = "classpath:fdfs.conf";
        String fileName = "/Users/picongzhi/Downloads/cheer.jpeg";
        FastDFSClient fastDFSClient = new FastDFSClient(conf);
        fastDFSClient.uploadFile(fileName);
    }
}
