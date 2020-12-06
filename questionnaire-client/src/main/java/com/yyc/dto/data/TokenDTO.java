package com.yyc.dto.data;

import lombok.Data;

import java.util.UUID;

/**
 * @author yuchengyao
 */
@Data
public class TokenDTO {

    /**
     * 令牌
     */
    private String token;

    /**
     *
     */
    private String openId;

    /**
     * 角色列表
     */
    private String[] roleCode;

    public static TokenDTO random() {
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(UUID.randomUUID().toString());

        return tokenDTO;
    }


}
