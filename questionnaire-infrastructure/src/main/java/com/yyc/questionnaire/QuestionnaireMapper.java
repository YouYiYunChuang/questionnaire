package com.yyc.questionnaire;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yyc.dto.data.QuestionnaireDTO;
import org.apache.ibatis.annotations.*;

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

    @Results(
            id = "questionnaireResultMap",
            value = {
                    @Result(property = "questionnaireCode", column = "questionnaire_code"),
                    @Result(property = "questionnaireQuestionDTOS", javaType = List.class, column = "questionnaire_code",
                            many = @Many(select = "com.yyc.question.QuestionnaireQuestionMapper.getQuestionnaireQuestionDetails"))
            })
    @Select("select * from questionnaire ${ew.customSqlSegment}")
    List<QuestionnaireDTO> getQuestionnaireDetails(@Param(Constants.WRAPPER) Wrapper wrapper);

}
