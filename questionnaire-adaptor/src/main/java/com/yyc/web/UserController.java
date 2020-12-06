package com.yyc.web;

import com.alibaba.cola.dto.SingleResponse;
import com.yyc.api.UserServicesI;
import com.yyc.dto.data.TokenDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yuchengyao
 */
@Api(value = "用户相关接口", tags = {"用户相关接口"})
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserServicesI userServicesI;

    /**
     * 小程序用户登录接口
     *
     * @param code
     * @return
     */
    @ApiOperation(value = "小程序用户登录接口", notes = "小程序用户登录接口")
    @PostMapping("login/{code}")
    public SingleResponse<TokenDTO> wechatLogin(@PathVariable("code") String code) {

        return SingleResponse.of(userServicesI.wechatLogin(code));
    }
}
