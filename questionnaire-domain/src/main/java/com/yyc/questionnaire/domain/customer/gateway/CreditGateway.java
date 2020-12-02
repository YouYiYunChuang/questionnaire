package com.yyc.questionnaire.domain.customer.gateway;

import com.yyc.questionnaire.domain.customer.Customer;
import com.yyc.questionnaire.domain.customer.Credit;

//Assume that the credit info is in antoher distributed Service
public interface CreditGateway {
    public Credit getCredit(String customerId);
}
