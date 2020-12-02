package com.yyc.questionnaire.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.yyc.questionnaire.dto.CustomerAddCmd;
import com.yyc.questionnaire.dto.CustomerListByNameQry;
import com.yyc.questionnaire.dto.data.CustomerDTO;

public interface CustomerServiceI {

    public Response addCustomer(CustomerAddCmd customerAddCmd);

    public MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry);
}
