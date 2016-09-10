package com.uzabase.controller;

import com.uzabase.bean.ColumnDataBean;
import com.uzabase.bean.PieDataBean;
import com.uzabase.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Trung on 3/29/2016 12:45 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
@Controller
public class ChartController {

    @Autowired
    ChartService chartService;

    @RequestMapping({"/", "/index"})
    public String showCharts() {
//        System.out.println("Option for checking: " + option.getOption());
        return "index";
    }

    @RequestMapping({"/chartPie"})
    @ResponseBody
    public PieDataBean showPieChartData() {
        return chartService.getPieChartData();
    }

    @RequestMapping({"/chartColumn"})
    @ResponseBody
    public ColumnDataBean showColumnChartData() {
        return chartService.getColumnChartData();
    }
}
