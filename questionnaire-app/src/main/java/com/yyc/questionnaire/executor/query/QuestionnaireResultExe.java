package com.yyc.questionnaire.executor.query;

import com.google.gson.reflect.TypeToken;
import com.yyc.domain.gateway.QuestionnaireQuestionReplicationGateway;
import com.yyc.domain.utils.JsonUtils;
import com.yyc.dto.QuestionnaireQuestionReplicationQry;
import com.yyc.dto.data.QuestionnaireDTO;
import com.yyc.dto.data.QuestionnaireQuestionDTO;
import com.yyc.dto.data.QuestionnaireQuestionItemDTO;
import com.yyc.dto.data.QuestionnaireQuestionReplicationDTO;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 问卷结果查询
 *
 * @author yuchengyao
 */
@Component
public class QuestionnaireResultExe {

    @Resource
    private QuestionnaireGetExe questionnaireGetExe;

    @Resource
    private QuestionnaireQuestionReplicationGateway questionnaireQuestionReplicationGateway;


    /**
     * 获取结果
     *
     * @return
     */
    public Map<String, List<List<Map<String, String>>>> getQuestionnaireResult(@NonNull String questionnaireCode) {

        Map<String, List<List<Map<String, String>>>> list = new Hashtable<>();

        //  构建表头
        List<List<Map<String, String>>> questionnaireResultHeader = getQuestionnaireResultHeader(questionnaireCode);

        list.put(QuestionnaireExportExe.HEADER, questionnaireResultHeader);

        //  构建数据
        list.put(QuestionnaireExportExe.BODY, getQuestionnaireResultData(questionnaireCode, questionnaireResultHeader));

        return list;
    }

    /**
     * 获取问卷表头(多级)
     *
     * @param questionnaireCode 问卷code
     * @return
     */
    private List<List<Map<String, String>>> getQuestionnaireResultHeader(@NonNull String questionnaireCode) {

        QuestionnaireDTO questionnaire = questionnaireGetExe.getQuestionnaire(questionnaireCode, null);

        // 一级表头 问卷标题
        List<Map<String, String>> questionnaireHeader = new ArrayList<>();

        // 二级表头 问题
        List<Map<String, String>> questionnaireQuestionHeader = new ArrayList<>();

        // 三级表头 问题细项
        List<Map<String, String>> questionnaireQuestionItemHeader = new ArrayList<>();

        List<QuestionnaireQuestionDTO> questionnaireQuestionDTOS = questionnaire.getQuestionnaireQuestionDTOS();

        List<QuestionnaireQuestionDTO> questionnaireQuestionDTOdata = questionnaireQuestionDTOS.stream().sorted(Comparator.comparing(QuestionnaireQuestionDTO::getQuestionnaireQuestionSort)).collect(Collectors.toList());

        questionnaireQuestionDTOdata.forEach(questionnaireQuestion -> {

            List<QuestionnaireQuestionItemDTO> questionnaireQuestionItemDTOdata = questionnaireQuestion.getQuestionnaireQuestionItemDTOS().stream().sorted(Comparator.comparing(QuestionnaireQuestionItemDTO::getQuestionnaireQuestionItemSort)).collect(Collectors.toList());

            questionnaireQuestionItemDTOdata.forEach(questionnaireQuestionItem -> {

                Map<String, String> questionnaireHeaderMap = new Hashtable<>();
                Map<String, String> questionnaireQuestionHeaderMap = new Hashtable<>();
                Map<String, String> questionnaireQuestionItemHeaderMap = new Hashtable<>();

                questionnaireHeaderMap.put(questionnaire.getQuestionnaireCode(), questionnaire.getQuestionnaireTitle());
                questionnaireQuestionHeaderMap.put(questionnaireQuestion.getQuestionnaireQuestionCode(), questionnaireQuestion.getQuestionnaireQuestionTitle());
                questionnaireQuestionItemHeaderMap.put(questionnaireQuestionItem.getQuestionnaireQuestionItemCode(), questionnaireQuestionItem.getQuestionnaireQuestionItemContent());

                questionnaireHeader.add(questionnaireHeaderMap);
                questionnaireQuestionHeader.add(questionnaireQuestionHeaderMap);
                questionnaireQuestionItemHeader.add(questionnaireQuestionItemHeaderMap);

            });
        });

        //  表头合并
        List<List<Map<String, String>>> list = new ArrayList<>();
        list.add(questionnaireHeader);
        list.add(questionnaireQuestionHeader);
        list.add(questionnaireQuestionItemHeader);

        return list;
    }

    /**
     * 获取问卷的答案数据
     *
     * @param questionnaireCode 问卷code
     * @param resultHeader      表头数据
     * @return
     */
    private List<List<Map<String, String>>> getQuestionnaireResultData(
            @NonNull String questionnaireCode,
            List<List<Map<String, String>>> resultHeader) {

        List<List<Map<String, String>>> result = new ArrayList<>();

        List<Map<String, String>> headerList = resultHeader.get(resultHeader.size() - 1);

        List<QuestionnaireQuestionReplicationDTO> questionnaireQuestionReplicationDTOS = questionnaireQuestionReplicationGateway.getQuestionnaireQuestionReplication(QuestionnaireQuestionReplicationQry.builder().questionnaireCode(questionnaireCode).build());

        questionnaireQuestionReplicationDTOS.forEach(questionnaireQuestionReplication -> {

            String questionnaireReplicationContent = questionnaireQuestionReplication.getQuestionnaireReplicationContent();

            Type type = new TypeToken<Map<String, String>>() {
            }.getType();

            Map<String, String> questionnaireReplicationContentMap = JsonUtils.parse(questionnaireReplicationContent, type);

            List<Map<String, String>> resultRow = new ArrayList<>();

            headerList.forEach(headerRow -> {

                Map<String, String> data = new HashMap<>(1);
                headerRow.forEach((key, value) -> {

                    String dataValue = questionnaireReplicationContentMap.get(key);
                    data.put(key, dataValue);
                });

                resultRow.add(data);
            });

            result.add(resultRow);

        });

        return result;
    }

}
