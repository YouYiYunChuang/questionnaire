package com.yyc.dto;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

/**
 * @author yuchengyao
 */
@Data
public class QuestionnaireQry extends PageQuery {

    /**
     * 问卷标题
     */
    private String questionnaireTitle;

    /**
     * 问卷code
     */
    private String questionnaireCode;

}
