package com.yyc.questionnaire;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.cola.dto.MultiResponse;
import com.google.gson.reflect.TypeToken;
import com.yyc.api.QuestionnaireServiceI;
import com.yyc.config.WechatConfig;
import com.yyc.domain.exception.QuestionnaireException;
import com.yyc.domain.exception.QuestionnaireExceptionCode;
import com.yyc.domain.utils.JsonUtils;
import com.yyc.dto.QuestionnaireInsertCmd;
import com.yyc.dto.QuestionnaireQry;
import com.yyc.dto.QuestionnaireReportCmd;
import com.yyc.dto.data.QuestionnaireDTO;
import com.yyc.questionnaire.executor.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuchengyao
 */
@Slf4j
@Service
public class QuestionnaireServiceImpl implements QuestionnaireServiceI {

    @Resource
    private QuestionnaireInsertExe questionnaireInsertExe;

    @Resource
    private QuestionnairesListExe questionnairesListExe;

    @Resource
    private QuestionnaireGetExe questionnaireGetExe;

    @Resource
    private QuestionnaireDeactivateExe questionnaireDeactivateExe;

    @Resource
    private QuestionnaireReportExe questionnaireReportExe;

    @Resource
    private QuestionnaireReportCheckExe questionnaireReportCheckExe;

    @Resource
    private WechatConfig wechatConfig;

    @Transactional
    @Override
    public void insert(@NonNull QuestionnaireInsertCmd questionnaireInsertCmd) {
        questionnaireInsertExe.insert(questionnaireInsertCmd);
    }

    @Override
    public MultiResponse<QuestionnaireDTO> listQuestionnaires(@NonNull QuestionnaireQry questionnaireInsertQry) {
        return questionnairesListExe.listQuestionnaires(questionnaireInsertQry);
    }

    @Override
    public QuestionnaireDTO getQuestionnaire(@NonNull String questionnaireCode) {
        return questionnaireGetExe.getQuestionnaire(questionnaireCode);
    }

    @Override
    public void deactivateQuestionnaire(@NonNull String questionnaireCode) {
        questionnaireDeactivateExe.deactivateQuestionnaire(questionnaireCode);
    }

    @Override
    @Transactional
    public void reportQuestionnaire(@NonNull QuestionnaireReportCmd questionnaireReportCmd) {
        questionnaireReportExe.reportQuestionnaire(questionnaireReportCmd);
    }

    @Override
    public void checkReportQuestionnaire(@NonNull String questionnaireCode) {
        questionnaireReportCheckExe.checkQuestionnaire(questionnaireCode);
    }

    @Override
    public byte[] shareQuestionnaire(@NonNull String scene, @NonNull String page) throws Exception {

        String accessToken = getAccessToken();

        Map<String, Object> data = new HashMap<>();

        data.put("scene", scene);
        data.put("page", page);

        log.info(String.format(WechatConfig.getUnlimitedUrl, accessToken));

        HttpResponse response = HttpRequest.post(String.format(WechatConfig.getUnlimitedUrl, accessToken)).body(JsonUtils.toString(data)).execute();

        String body = response.body();

        Type type = new TypeToken<Map<String, String>>() {
        }.getType();

        Map<String, String> parse = JsonUtils.parse(HttpUtil.get(body), type);

        if ("41030".equals(parse.get("errcode"))) {
            throw new QuestionnaireException(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_SYSTEM_EXCEPTION);
        }

        return readInputStream(response.bodyStream());

    }

    /**
     * 将流 保存为数据数组
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 把outStream里的数据写入内存
        return outStream.toByteArray();
    }


    private String getAccessToken() {

        String formatUrl = String.format(WechatConfig.accessTokenUrl, wechatConfig.getAppID(), wechatConfig.getAppSecret());

        Type type = new TypeToken<Map<String, String>>() {
        }.getType();

        Map<String, String> parse = JsonUtils.parse(HttpUtil.get(formatUrl), type);

        return parse.get("access_token");
    }
}
