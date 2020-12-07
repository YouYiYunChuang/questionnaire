package com.yyc.question;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyc.dto.data.QuestionnaireQuestionDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author yuchengyao
 */
@Mapper
public interface QuestionnaireQuestionMapper extends BaseMapper<QuestionnaireQuestionDO> {


    /**
     * 获取问卷问题详情
     *
     * @param questionnaireCode
     * @return
     */
    @Results(
            id = "questionnaireQuestionResultMap",
            value = {
                    @Result(property = "questionnaireQuestionCode", column = "questionnaire_question_code"),
                    @Result(property = "questionnaireQuestionItemDTOS", javaType = List.class, column = "questionnaire_question_code",
                            many = @Many(select = "com.yyc.question.QuestionnaireQuestionItemMapper.getQuestionnaireQuestionItemDetails"))
            })
    @Select("select * from questionnaire_question where questionnaire_code = #{questionnaireCode}")
    List<QuestionnaireQuestionDTO> getQuestionnaireQuestionDetails(@Param("questionnaireCode") String questionnaireCode);

}
