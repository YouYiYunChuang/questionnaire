package com.yyc.domain.utils;

import java.util.UUID;

/**
 * @author yuchengyao
 */
public class CodeBuildUtils {

    /**
     * 获取code
     *
     * @return
     */
    public static String getCode() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取场景 小程序二维码获取需要数字唯一标识（长度在 30 以内）
     *
     * @return
     */
    public static String getScene() {
        return String.format("%s%s", DateTimeUtils.getSysCurrentTimeMillis(), RandomNumberUtil.createRandomNumber(5));
    }
}