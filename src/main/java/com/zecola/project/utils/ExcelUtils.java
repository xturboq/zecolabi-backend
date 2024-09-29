package com.zecola.project.utils;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class ExcelUtils {


    public static String excelToCsv(MultipartFile multipartFile) {
//    File file = ResourceUtils.getFile("classpath:test_excel.xlsx");
//    List<Map<Integer, String>> list = EasyExcel.read(file)
//            .excelType(ExcelTypeEnum.XLSX)
//            .sheet()
//            .headRowNumber(0)
//            .doReadSync();
//        System.out.println(list);
        List<Map<Integer, String>> list = null;
        try {
            list = EasyExcel.read(multipartFile.getInputStream())
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();
        } catch (IOException e) {
            log.error("excel to csv error", e);
        }
        if (CollUtil.isEmpty(list)) {
            return "";
        }
        //转换 csv
        StringBuilder sb = new StringBuilder();
        //调取表头
        LinkedHashMap<Integer, String> headerMap = (LinkedHashMap) list.get(0);
        List<String> headerList = headerMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
        sb.append(StringUtils.join(headerList, ",")).append("\n");

        //读取数据
        for (int i = 0; i < list.size(); i++) {
            LinkedHashMap<Integer, String> rowMap = (LinkedHashMap) list.get(i);
            List<String> rowList = rowMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
            sb.append(StringUtils.join(rowList, ",")).append("\n");

        }
        return sb.toString();



    }

}
