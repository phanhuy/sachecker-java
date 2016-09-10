package com.uzabase.service;

import com.uzabase.bean.ColumnDataBean;
import com.uzabase.bean.PieDataBean;
import com.uzabase.entity.Option;
import com.uzabase.entity.Url;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by Trung on 3/29/2016 12:39 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
@Service
public interface ChartService {

    void check(Url url, Option option) throws IOException;

    PieDataBean getPieChartData();

    ColumnDataBean getColumnChartData();
}
