package com.yyc.questionnaire.executor;

import cn.hutool.core.collection.CollectionUtil;
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
import com.yyc.dto.QuestionnaireQry;
import com.yyc.dto.data.QuestionnaireDTO;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuchengyao
 */
@Slf4j
@Component
public class QuestionnaireShareExe {

    @Resource
    private WechatConfig wechatConfig;

    @Resource
    private QuestionnaireServiceI questionnaireServiceI;

    /**
     * 分享问卷返回二维码的baseCode
     *
     * @return
     */
    public byte[] shareQuestionnaire(@NonNull String scene, @NonNull String page) throws Exception {
        HttpResponse response = getShareHttpResponse(scene, page);
        return readInputStream(buildImageTitleInputStream(getQuestionnaireTitle(scene), response.bodyStream()));
    }

    /**
     * 获取问卷的标题
     *
     * @param scene
     * @return
     */
    private String getQuestionnaireTitle(String scene) {

        QuestionnaireQry questionnaireQry = new QuestionnaireQry();
        questionnaireQry.setQuestionnaireScene(scene);
        MultiResponse<QuestionnaireDTO> questionnaireDTOMultiResponse = questionnaireServiceI.listQuestionnaires(questionnaireQry);

        if (questionnaireDTOMultiResponse == null || CollectionUtil.isEmpty(questionnaireDTOMultiResponse.getData())) {
            throw new QuestionnaireException(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_DATA_EXCEPTION);
        }

        return questionnaireDTOMultiResponse.getData().get(0).getQuestionnaireTitle();
    }

    private InputStream buildImageTitleInputStream(String title, InputStream inputStream) throws IOException {

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(this.createNewPic(title, ImageIO.read(inputStream)), "jpg", os);

        return new ByteArrayInputStream(os.toByteArray());
    }


    private HttpResponse getShareHttpResponse(String scene, String page) {

        String accessToken = getAccessToken();

        Map<String, Object> data = new HashMap<>();
        data.put("scene", scene);
        data.put("page", page);

        HttpResponse response = HttpRequest.post(String.format(WechatConfig.getUnlimitedUrl, accessToken)).body(JsonUtils.toString(data)).execute();

        String body = response.body();

        try {
            Type type = new TypeToken<Map<String, String>>() {
            }.getType();

            Map<String, String> parse = JsonUtils.parse(body, type);

            log.info("请求返回：{}", body);

            throw new QuestionnaireException(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_SYSTEM_EXCEPTION);

        } catch (Exception e) {

        }
        return response;
    }

    public BufferedImage createNewPic(String title, BufferedImage logo) {

        BufferedImage image = new BufferedImage(500, 550, BufferedImage.TYPE_INT_RGB);

        //设置图片的背景色
        Graphics2D main = image.createGraphics();
        main.setColor(Color.white);
        main.fillRect(0, 0, 500, 550);

        //***********************插入中间广告图
        Graphics mainPic = image.getGraphics();

        if (logo != null) {
            mainPic.drawImage(logo, 40, 40, 400, 400, null);
            mainPic.dispose();
        }

        //***********************页面头部
        Graphics titleG = image.createGraphics();


        //设置区域颜色
        //titleG.setColor(Color.white);
        //填充区域并确定区域大小位置
        //titleG.fillRect(450, 50, 450, 50);
        //设置字体颜色，先设置颜色，再填充内容
        titleG.setColor(Color.BLACK);
        //设置字体
        Font titleFont = new Font("宋体", Font.BOLD, 22);
        titleG.setFont(titleFont);

        FontMetrics fm = titleG.getFontMetrics();

        titleG.drawString(title, (image.getWidth(null) - fm.stringWidth(title)) / 2, 500);

        return image;
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
