package com.yyc.user;

import com.yyc.api.UserServicesI;
import com.yyc.config.WechatConfig;
import com.yyc.dto.data.TokenDTO;
import com.yyc.user.executor.UserLoginWechatExe;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yuchengyao
 */
@Service
public class UserServicesImpl implements UserServicesI {

    @Resource
    private WechatConfig wechatConfig;

    @Resource
    private UserLoginWechatExe userLoginWechatExe;

    @Override
    public TokenDTO wechatLogin(String code) {
        return userLoginWechatExe.loginWechatUser(code);
    }


}