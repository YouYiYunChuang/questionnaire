package com.yyc.api;

import com.yyc.dto.data.TokenDTO;

/**
 * @author yuchengyao
 */
public interface UserServicesI {

    /**
     * 微信登录
     *
     * @param code 登录code
     * @return
     */
    TokenDTO wechatLogin(String code);

}
