package com.yyc.dto;

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
public class QuestionnaireQuestionReplicationQry {

    private String questionnaireCode;

    private String openId;
}
