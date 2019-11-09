package com.pcz.taotao.common.utils;

import java.util.Random;

/**
 * @author picongzhi
 */
public class IDUtils {
    /**
     * 生成图片名
     *
     * @return 图片名
     */
    public static String genImageName() {
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end3 = random.nextInt(999);
        String str = millis + String.format("%03d", end3);

        return str;
    }

    /**
     * 生成商品id
     *
     * @return 商品id
     */
    public static long genItemId() {
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end2 = random.nextInt(99);
        String str = millis + String.format("%02d", end2);
        long id = new Long(str);

        return id;
    }
}
