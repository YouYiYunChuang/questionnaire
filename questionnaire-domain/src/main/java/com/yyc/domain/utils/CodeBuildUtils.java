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
}
