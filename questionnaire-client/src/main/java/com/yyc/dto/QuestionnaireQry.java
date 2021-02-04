package com.yyc.dto;

import com.alibaba.cola.dto.PageQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuchengyao
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireQry extends PageQuery {

    /**
     * 问卷标题
     */
    private String questionnaireTitle;

    /**
     * 问卷code
     */
    private String questionnaireCode;

    /**
     * 场景码
     */
    private String questionnaireScene;

}
