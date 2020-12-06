package com.yyc.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuchengyao
 */
@Data
@Configuration
public class WechatConfig {

    /**
     * 获取openID URL
     */
    public static String WxGetOpenIdUrl = "https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 用户端小程序 AppId
     */
    @Value("${wechat.appID:NULL}")
    private String appID;

    /**
     * 用户端小程序 AppSecret
     */
    @Value("${wechat.appSecret:NULL}")
    private String appSecret;

}
