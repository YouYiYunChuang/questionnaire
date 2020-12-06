package com.yyc.questionnaire;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yyc.dto.data.QuestionnaireDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yuchengyao
 */
@Mapper
public interface QuestionnaireMapper extends BaseMapper<QuestionnaireDO> {

    /**
     * 获取问卷详情
     *
     * @param wrapper
     * @return
     */
    @Select("select * " +
            "from questionnaire questionnaire " +
            "left join questionnaire_question questionnaire_question on questionnaire.questionnaire_code = questionnaire_question.questionnaire_code " +
            "left join questionnaire_question_item questionnaire_question_item on questionnaire_question.questionnaire_question_code = questionnaire_question_item.questionnaire_question_code " +
            "${ew.customSqlSegment}")
    List<QuestionnaireDTO> getQuestionnaireDetails(@Param(Constants.WRAPPER) Wrapper wrapper);
}
