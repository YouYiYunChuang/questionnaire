package com.yyc.questionnaire;

import com.alibaba.cola.dto.MultiResponse;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyc.domain.gateway.QuestionnaireGateway;
import com.yyc.domain.utils.CollectionCopyUtil;
import com.yyc.dto.QuestionnaireQry;
import com.yyc.dto.data.QuestionnaireDTO;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class QuestionnaireGatewayImpl implements QuestionnaireGateway {

    @Resource
    private QuestionnaireMapper questionnaireMapper;


    @Override
    public QuestionnaireDTO getQuestionnaire(QuestionnaireQry questionnaireQry) {

        List<QuestionnaireDO> questionnaireDOS = new ArrayList<>();

        questionnaireDOS = questionnaireMapper.selectList(buildWrapper(questionnaireQry));

        checkReturn(questionnaireDOS);

        QuestionnaireDTO questionnaireDTO = new QuestionnaireDTO();

        BeanUtils.copyProperties(questionnaireDOS.get(0), questionnaireDTO);


        //

        return questionnaireDTO;
    }

    @Override
    public MultiResponse<QuestionnaireDTO> listQuestionnaires(QuestionnaireQry questionnaireQry) {

        List<QuestionnaireDO> questionnaireDOS = new ArrayList<>();

        IPage<QuestionnaireDO> userPage = new Page<>(questionnaireQry.getPageNum(), questionnaireQry.getPageSize());

        IPage iPage = questionnaireMapper.selectPage(userPage, buildWrapper(questionnaireQry));

        List records = iPage.getRecords();

        return MultiResponse.of(CollectionCopyUtil.copyList(records, QuestionnaireDTO.class), Integer.parseInt(String.valueOf(iPage.getTotal())));
    }

    private Wrapper buildWrapper(QuestionnaireQry questionnaireQry) {

        Wrapper wrapper = new QueryWrapper()
                .eq(false, "questionnaire_code", questionnaireQry.getQuestionnaireCode())
                .like(false, "questionnaireTitle", questionnaireQry.getQuestionnaireTitle());

        return wrapper;
    }

    private void checkReturn(List<QuestionnaireDO> questionnaireDOS) {
        if (questionnaireDOS == null || questionnaireDOS.isEmpty() || questionnaireDOS.size() > 2) {
            //  返回值异常
            //  TODO:抛出异常
        }
    }
}
