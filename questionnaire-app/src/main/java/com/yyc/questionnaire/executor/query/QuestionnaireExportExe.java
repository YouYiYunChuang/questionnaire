package com.yyc.questionnaire.executor.query;

import com.alibaba.excel.EasyExcel;
import com.google.gson.reflect.TypeToken;
import com.yyc.domain.utils.JsonUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 问卷导出
 *
 * @author yuchengyao
 */
@Component
public class QuestionnaireExportExe {

    public static final String HEADER = "HEADER";

    public static final String BODY = "BODY";

    /**
     * 导出问卷结果
     */
    public void exportQuestionnaireResult(Map<String, List<List<Map<String, String>>>> questionnaireResult, HttpServletResponse response) throws Exception {

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + "调查问卷回答详情" + ".xlsx");

        //  表头
        List<List<Map<String, String>>> headData = questionnaireResult.get(HEADER);

        //  表数据
        List<List<Map<String, String>>> bodyData = questionnaireResult.get(BODY);

        EasyExcel.write(response.getOutputStream()).sheet(0, "调查问卷回答详情").head(getHead(headData)).doWrite(getBody(bodyData));

    }

    private List<List<String>> getHead(List<List<Map<String, String>>> headData) {

        List<List<String>> headResult = new ArrayList<>();

        headData.forEach(head -> {
            List<String> addRow = new ArrayList<>();
            head.forEach(row -> {
                addRow.add(String.join("、", row.values()));
            });
            headResult.add(addRow);
        });

        return reverseList(headResult);

    }

    private List<List<String>> getBody(List<List<Map<String, String>>> bodyData) {

        List<List<String>> bodyResult = new ArrayList<>();

        bodyData.forEach(head -> {
            List<String> addRow = new ArrayList<>();
            head.forEach(row -> {
                row.forEach((key, value) -> {
                    addRow.add(value);
                });
            });
            bodyResult.add(addRow);
        });

        return bodyResult;
    }

    private List<List<String>> reverseList(List<List<String>> reverseList) {

        int size = reverseList.get(0).size();
        int size1 = reverseList.size();

        //  转换--------------
        Type type = new TypeToken<String[][]>() {
        }.getType();

        String[][] internalArray = JsonUtils.parse(JsonUtils.toString(reverseList), type);
        //  转换--------------


        String[][] array = internalArray;
        String[][] toArray = new String[size][size1];

        int k = 0;
        for (String[] x : array) {
            for (int i = 0; i < x.length; i++) {
                System.out.print(x[i] + " ");
                toArray[i][k] = array[k][i];
            }
            k++;
            System.out.println("");
        }

        for (String[] x : toArray) {
            for (int i = 0; i < x.length; i++) {
                System.out.print(x[i] + " ");
            }
            k++;
            System.out.println("");
        }


        //  转换--------------
        Type resultType = new TypeToken<List<List<String>>>() {
        }.getType();

        List<List<String>> toReverseList = JsonUtils.parse(JsonUtils.toString(toArray), resultType);
        //  转换--------------

        return toReverseList;
    }

}
