package com.yyc.questionnaire.command;

import com.alibaba.cola.dto.Response;
import com.yyc.questionnaire.convertor.UserProfileConvertor;
import com.yyc.questionnaire.domain.user.UserProfile;
import com.yyc.questionnaire.dto.UserProfileUpdateCmd;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserProfileUpdateCmdExe{

    @Resource
    private UserProfileGateway userProfileGateway;

    public Response execute(UserProfileUpdateCmd cmd) {
        UserProfile userProfile = UserProfileConvertor.toEntity(cmd.getUserProfileCO());
        userProfileGateway.update(userProfile);
        return Response.buildSuccess();
    }
}