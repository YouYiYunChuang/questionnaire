package com.yyc.web;

import com.alibaba.cola.dto.SingleResponse;
import com.yyc.api.UserServicesI;
import com.yyc.dto.data.TokenDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yuchengyao
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserServicesI userServicesI;

    /**
     * 微信小程序登录
     *
     * @param code
     * @return
     */
    @PostMapping("login/{code}")
    public SingleResponse<TokenDTO> wechatLogin(@PathVariable("code") String code) {

        return SingleResponse.of(userServicesI.wechatLogin(code));
    }
}
