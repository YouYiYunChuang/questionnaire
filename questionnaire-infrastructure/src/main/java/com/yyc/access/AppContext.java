package com.yyc.access;

import com.yyc.dto.data.TokenDTO;

/**
 * @author yuchengyao
 */
public class AppContext {

    private static final ThreadLocal<TokenDTO> localToken = new ThreadLocal<TokenDTO>();

    private static TokenDTO emptyToken = null;

    static {
        emptyToken = new TokenDTO();
    }

    public static TokenDTO getTokenDTO() {

        TokenDTO principal = localToken.get();
        if (principal == null) {
            return emptyToken;
        } else {
            return principal;
        }
    }

    public static void setPrincipal(TokenDTO principal) {
        localToken.set(principal);
    }

    public static void cleanPrincipal() {
        localToken.set(emptyToken);
    }

}
