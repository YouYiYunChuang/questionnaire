package com.yyc.questionnaire.repository.db;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyc.questionnaire.QuestionnaireDO;
import com.yyc.questionnaire.QuestionnaireMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionnaireDBMapper extends QuestionnaireMapper, BaseMapper<QuestionnaireDO> {

}
