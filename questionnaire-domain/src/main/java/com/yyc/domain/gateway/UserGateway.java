package com.yyc.domain.gateway;

import com.yyc.dto.UserInsertCmd;

/**
 * @author yuchengyao
 */
public interface UserGateway {

    /**
     * 新增用户
     *
     * @param userInsertCmd
     */
    void insert(UserInsertCmd userInsertCmd);

    /**
     * 获取用户
     *
     * @param openId 微信openid
     * @return
     */
    boolean getByOpenId(String openId);
}
