package com.yyc.user.executor;

import com.yyc.access.Role;
import com.yyc.config.RedisUtils;
import com.yyc.config.WechatConfig;
import com.yyc.domain.exception.QuestionnaireException;
import com.yyc.domain.exception.QuestionnaireExceptionCode;
import com.yyc.domain.gateway.UserGateway;
import com.yyc.domain.user.User;
import com.yyc.domain.utils.JsonUtils;
import com.yyc.domain.utils.UrlUtil;
import com.yyc.dto.UserInsertCmd;
import com.yyc.dto.data.TokenDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yuchengyao
 */
@Component
public class UserLoginWechatExe {

    @Resource
    private WechatConfig wechatConfig;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private UserGateway userGateway;

    public TokenDTO loginWechatUser(String code) {

        String openId = getOpenId(code);
        User user = new User();
        user.setOpenId(openId);


        if (!userGateway.getByOpenId(openId)) {
            insertUser(openId);
        }

        //  有用户
        TokenDTO token = TokenDTO.random();
        token.setOpenId(openId);

        //  角色
        token.setRoleCode(new String[]{Role.GENERAL_USER.name()});

        //  token 缓存60分钟
        redisUtils.setString(token.getToken(), JsonUtils.toString(token), 60L, TimeUnit.MINUTES);

        return token;
    }

    void insertUser(String openId) {
        //  无用户，先新建
        UserInsertCmd userInsertCmd = new UserInsertCmd();
        userInsertCmd.setOpenId(openId);
        userGateway.insert(userInsertCmd);
    }

    private String getOpenId(String code) {
        WeChatConfigCmd weChatConfigCmd = new WeChatConfigCmd();

        weChatConfigCmd.setAppId(wechatConfig.getAppID());
        weChatConfigCmd.setAppSecret(wechatConfig.getAppSecret());
        weChatConfigCmd.setJsCode(code);

        Map<String, String> res = new HashMap<>();

        //开发者设置中的appId
        res.put("appid", weChatConfigCmd.getAppId());

        //开发者设置中的appSecret  
        res.put("secret", weChatConfigCmd.getAppSecret());

        //小程序调用wx.login返回的code  
        res.put("js_code", weChatConfigCmd.getJsCode());

        //默认参数  
        res.put("grant_type", "authorization_code");

        WeChatConfigCmd parse = JsonUtils.parse(UrlUtil.sendPost(WechatConfig.WxGetOpenIdUrl, res), WeChatConfigCmd.class);

        String openid = parse.getOpenid();

        if (openid == null) {
            throw new QuestionnaireException(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_WECHAT_OPEN_ID_EXCEPTION);
        }
        return openid;
    }
}

@Data
class WeChatConfigCmd {
    private String appId;
    private String appSecret;
    private String jsCode;
    private String openid;
}