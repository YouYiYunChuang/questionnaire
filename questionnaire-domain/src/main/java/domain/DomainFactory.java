package com.yyc.questionnaire.domain;

import com.yyc.questionnaire.domain.user.UserProfile;

public class DomainFactory {

    public static UserProfile getUserProfile(){
        return new UserProfile();
    }

}
