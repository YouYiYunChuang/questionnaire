package com.yyc.question;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyc.dto.data.QuestionnaireQuestionItemDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author yuchengyao
 */
@Mapper
public interface QuestionnaireQuestionItemMapper extends BaseMapper<QuestionnaireQuestionItemDO> {

    /**
     * 获取问卷问题详情
     *
     * @param questionnaireQuestionCode
     * @return
     */
    @Results(
            id = "questionnaireQuestionItemResultMap",
            value = {
                    @Result(property = "questionnaireQuestionCode", column = "questionnaire_question_code")
            })
    @Select("select * from questionnaire_question_item where questionnaire_question_code = #{questionnaireQuestionCode}")
    List<QuestionnaireQuestionItemDTO> getQuestionnaireQuestionItemDetails(@Param("questionnaireQuestionCode") String questionnaireQuestionCode);

}
