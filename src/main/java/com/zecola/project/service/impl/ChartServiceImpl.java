package com.zecola.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zecola.project.model.entity.Chart;
import com.zecola.project.service.ChartService;
import com.zecola.project.mapper.ChartMapper;
import org.springframework.stereotype.Service;

/**
* @author XTurbo
* @description 针对表【chart(图表信息表)】的数据库操作Service实现
* @createDate 2024-09-23 22:37:53
*/
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart>
    implements ChartService{

}




