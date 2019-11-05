package com.pcz.taotao.controller;

import com.pcz.taotao.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author picongzhi
 */
@Controller
public class PictureController {
    @Value("${IMAGE_SERVER_URL}")
    private String imageServerUrl;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public Map<String, Object> pictureUpload(MultipartFile uploadFile) {
        String originFilename = uploadFile.getOriginalFilename();
        String extName = originFilename.substring(originFilename.lastIndexOf(".") + 1);
        Map<String, Object> result = new HashMap<>();
        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:fdfs.conf");
            String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            url = imageServerUrl + url;
            result.put("error", 0);
            result.put("url", url);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("error", 0);
            result.put("message", "图片上传失败");
        }

        return result;
    }
}
