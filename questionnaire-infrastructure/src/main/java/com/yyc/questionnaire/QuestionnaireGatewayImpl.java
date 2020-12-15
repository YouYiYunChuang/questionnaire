package com.yyc.questionnaire;

import com.alibaba.cola.dto.MultiResponse;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyc.access.AppContext;
import com.yyc.domain.exception.QuestionnaireException;
import com.yyc.domain.exception.QuestionnaireExceptionCode;
import com.yyc.domain.gateway.QuestionnaireGateway;
import com.yyc.domain.status.DataStatus;
import com.yyc.domain.utils.CollectionCopyUtil;
import com.yyc.dto.QuestionnaireInsertCmd;
import com.yyc.dto.QuestionnaireQry;
import com.yyc.dto.QuestionnaireUpdateCmd;
import com.yyc.dto.data.QuestionnaireDTO;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuchengyao
 */
@Service
public class QuestionnaireGatewayImpl implements QuestionnaireGateway {

    @Resource
    private QuestionnaireMapper questionnaireMapper;

    @Override
    public QuestionnaireDTO getQuestionnaire(QuestionnaireQry questionnaireQry) {

        List<QuestionnaireDTO> questionnaireDOS = questionnaireMapper.getQuestionnaireDetails(buildQueryWrapper(questionnaireQry, false));

        buildData(questionnaireDOS);

        checkReturn(questionnaireDOS);

        return questionnaireDOS.get(0);
    }

    @Override
    public MultiResponse<QuestionnaireDTO> listQuestionnaires(QuestionnaireQry questionnaireQry) {

        IPage<QuestionnaireDO> userPage = new Page<>(questionnaireQry.getPageNum(), questionnaireQry.getPageSize());

        IPage iPage = questionnaireMapper.selectPage(userPage, buildQueryWrapper(questionnaireQry, true));

        return MultiResponse.of(CollectionCopyUtil.copyList(iPage.getRecords(), QuestionnaireDTO.class), Integer.parseInt(String.valueOf(iPage.getTotal())));
    }

    @Override
    public void deactivateQuestionnaire(String questionnaireCode) {

        QuestionnaireUpdateCmd questionnaireUpdateCmd = new QuestionnaireUpdateCmd();
        questionnaireUpdateCmd.setQuestionnaireCode(questionnaireCode);

        QuestionnaireDO questionnaireDO = new QuestionnaireDO();
        questionnaireDO.setStatus(DataStatus.DEACTIVATE.getCode());

        questionnaireMapper.update(questionnaireDO, buildUpdateWrapper(questionnaireUpdateCmd));
    }

    @Override
    public void insert(QuestionnaireInsertCmd questionnaireInsertCmd) {

        QuestionnaireDO questionnaireDO = new QuestionnaireDO();

        BeanUtils.copyProperties(questionnaireInsertCmd, questionnaireDO);

        questionnaireDO.setStatus(DataStatus.NEW.getCode());

        questionnaireMapper.insert(questionnaireDO);
    }

    private void buildData(List<QuestionnaireDTO> questionnaireDOS) {
        List<QuestionnaireDTO> questionnaireDTOList = new ArrayList<>();


        questionnaireDOS = questionnaireDTOList;
    }

    private Wrapper buildUpdateWrapper(QuestionnaireUpdateCmd questionnaireUpdateCmd) {

        Wrapper wrapper = new QueryWrapper()
                .eq(questionnaireUpdateCmd.getQuestionnaireCode() != null, "questionnaire_code", questionnaireUpdateCmd.getQuestionnaireCode())
                .ne(true, "questionnaire.status", 21);

        return wrapper;
    }

    private Wrapper buildQueryWrapper(@NonNull QuestionnaireQry questionnaireQry, boolean isAccess) {

        String openId = AppContext.getTokenDTO().getOpenId();

        String accessSql = String.format("select questionnaire_question_replication.questionnaire_code FROM questionnaire_question_replication where questionnaire_question_replication.open_id = '%s'", openId);

        Wrapper wrapper = new QueryWrapper()
                .eq(questionnaireQry.getQuestionnaireCode() != null, "questionnaire.questionnaire_code", questionnaireQry.getQuestionnaireCode())
                .eq(questionnaireQry.getQuestionnaireScene() != null, "questionnaire.questionnaire_scene", questionnaireQry.getQuestionnaireScene())
                .like(questionnaireQry.getQuestionnaireTitle() != null, "questionnaire.questionnaireTitle", questionnaireQry.getQuestionnaireTitle())
                .ne(true, "questionnaire.status", 21)
                .notInSql(isAccess, "questionnaire.questionnaire_code", accessSql);

        return wrapper;
    }

    private void checkReturn(List<QuestionnaireDTO> questionnaireDOS) {
        if (questionnaireDOS == null || questionnaireDOS.isEmpty() || questionnaireDOS.size() > 2) {
            //  返回值异常
            throw new QuestionnaireException(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_DATA_EXCEPTION);
        }
    }
}
