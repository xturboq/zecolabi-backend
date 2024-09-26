package com.zecola.project.model.vo;

import lombok.Data;

/**
 * BI 的返回结果
 */
@Data
public class BiResponse {
    /**
     * 网页图表代码
     */
    private String genChart;
    /**
     * 数据分析结果
     */
    private String genResult;
    /**
     * 图标类型
     */
    private String chartId;
}
