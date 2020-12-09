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
     * 通过该接口生成的小程序码，永久有效，数量暂无限制
     */
    public static String getUnlimitedUrl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN";

    /**
     *accessTokenUrl
     */
    public static String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

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
