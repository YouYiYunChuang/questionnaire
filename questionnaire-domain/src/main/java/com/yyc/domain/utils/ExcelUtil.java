package com.yyc.domain.utils;


import com.alibaba.excel.EasyExcel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * Excel 工具类
 */
public class ExcelUtil {

    public static void writeExcel(HttpServletResponse response, List<?> list, String fileName, String sheetName, Class clzz) throws Exception {
        EasyExcel.write(getOutputStream(fileName, response), clzz)
                .sheet(sheetName)
                .doWrite(list);
    }

    public static OutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            //response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "max-age=0");
            return response.getOutputStream();
        } catch (IOException e) {
            throw new Exception("导出excel表格失败!", e);
        }
    }
}
