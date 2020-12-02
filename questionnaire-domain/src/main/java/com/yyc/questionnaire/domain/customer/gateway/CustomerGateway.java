package com.yyc.questionnaire.domain.customer.gateway;

import com.yyc.questionnaire.domain.customer.Customer;

public interface CustomerGateway {
    public Customer getByById(String customerId);
}
