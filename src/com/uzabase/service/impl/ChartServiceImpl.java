package com.uzabase.service.impl;

import com.uzabase.accessibility.checker.Evaluator;
import com.uzabase.accessibility.checker.service.Rule;
import com.uzabase.bean.ColumnDataBean;
import com.uzabase.bean.ColumnSeriesBean;
import com.uzabase.bean.PieDataBean;
import com.uzabase.bean.PieSeriesBean;
import com.uzabase.entity.ASEvaluator;
import com.uzabase.entity.Option;
import com.uzabase.entity.Url;
import com.uzabase.security.checker.utils.IssueSecurity;
import com.uzabase.security.checker.utils.SecurityType;
import com.uzabase.service.ChartService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 3/29/2016 12:44 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
@Service
public class ChartServiceImpl implements ChartService {

    @Override
    public void check(Url url, Option option) throws IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("Starting new check");
        ASEvaluator.load(url);
        System.out.println("End load URL");
        switch (option.getOption()) {
            case 1:
                ASEvaluator.checkAccessibility();
                System.out.println("End check for Accessibility");
                ASEvaluator.checkSecurity();
                System.out.println("End check for Security");
                break;
            case 2:
                ASEvaluator.checkAccessibility();
                System.out.println("End check for Accessibility");
                break;
            case 3:
                ASEvaluator.checkSecurity();
                System.out.println("End check for Security");
                break;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("completed check!: " + (endTime - startTime));
    }

    @Override
    public PieDataBean getPieChartData() {
        List<PieSeriesBean> list = new ArrayList<>();

        int errorAccessibility = 0, normalAccessibility = 0;
        errorAccessibility = ASEvaluator.issues.size();
        normalAccessibility = ASEvaluator.evaluator.getElementsChecked() - errorAccessibility;

        System.out.println("Error size: " + errorAccessibility);
        System.out.println("Normal size: " + normalAccessibility);

        int errorSecurity = 0, warningSecurity = 0, normalSecurity = 0;
        for (IssueSecurity issue : ASEvaluator.fuzzer.getIssues()) {
            if (issue.getSeverity() == IssueSecurity.Severity.ERROR)
                errorSecurity += 1;
            else if (issue.getSeverity() == IssueSecurity.Severity.WARNING)
                warningSecurity += 1;
            else normalSecurity += 1;
        }

        int total = errorAccessibility + normalAccessibility + errorSecurity + warningSecurity + normalSecurity;

        list.add(new PieSeriesBean("Accessible Error", (errorAccessibility*100/total)));
        list.add(new PieSeriesBean("Security Error", (errorSecurity*100/total)));
        list.add(new PieSeriesBean("Security Warning", (warningSecurity*100/total)));
        list.add(new PieSeriesBean("Accessible Normal", (normalAccessibility*100/total)));
        list.add(new PieSeriesBean("Security Normal", (normalSecurity*100/total)));

        return new PieDataBean("container1", "Percent Error Graph", list);
    }

    @Override
    public ColumnDataBean getColumnChartData() {
        List<ColumnSeriesBean> series = new ArrayList<>();

        int temp1 = 0, temp2 = 0;

        for (Rule rule: Evaluator.rulesOperable.keySet()) {
            temp1 += Evaluator.rulesOperable.get(rule).getTotal();
            temp2 += Evaluator.rulesOperable.get(rule).getError();
        }

        int temp3 = 0, temp4 = 0;

        for (Rule rule: Evaluator.rulesPerceivable.keySet()) {
            temp3 += Evaluator.rulesPerceivable.get(rule).getTotal();
            temp4 += Evaluator.rulesPerceivable.get(rule).getError();
        }
        int temp5 = 0, temp6 = 0;

        for (Rule rule: Evaluator.rulesRobust.keySet()) {
            temp5 += Evaluator.rulesRobust.get(rule).getTotal();
            temp6 += Evaluator.rulesRobust.get(rule).getError();
        }
        int temp7 = 0, temp8 = 0;

        for (Rule rule: Evaluator.rulesUnderstandable.keySet()) {
            temp7 += Evaluator.rulesUnderstandable.get(rule).getTotal();
            temp8 += Evaluator.rulesUnderstandable.get(rule).getError();
        }

        int e1 = 0, w1 = 0, n1 = 0, e2 = 0, w2 = 0, n2 = 0, e3 = 0, w3 = 0, n3 = 0, e4 = 0, w4 = 0, n4 = 0;
        for (IssueSecurity issue : ASEvaluator.fuzzer.getIssues()) {
            switch (issue.getSeverity()) {
                case ERROR:
                    if (issue.getType() == SecurityType.SQL_INJECTION)
                        e1 ++;
                    if (issue.getType() == SecurityType.XSS)
                        e2 ++;
                    if (issue.getType() == SecurityType.XML_INJECTION)
                        e3 ++;
                    if (issue.getType() == SecurityType.XPATH_INJECTION)
                        e4 ++;
                    break;
                case WARNING:
                    if (issue.getType() == SecurityType.SQL_INJECTION)
                        w1 ++;
                    if (issue.getType() == SecurityType.XSS)
                        w2 ++;
                    if (issue.getType() == SecurityType.XML_INJECTION)
                        w3 ++;
                    if (issue.getType() == SecurityType.XPATH_INJECTION)
                        w4 ++;
                    break;
                case NORMAL:
                    if (issue.getType() == SecurityType.SQL_INJECTION)
                        n1 ++;
                    if (issue.getType() == SecurityType.XSS)
                        n2 ++;
                    if (issue.getType() == SecurityType.XML_INJECTION)
                        n3 ++;
                    if (issue.getType() == SecurityType.XPATH_INJECTION)
                        n4 ++;
                    break;
            }
        }

        series.add(new ColumnSeriesBean("Error", new double[] {temp2, temp4, temp6, temp8, e1, e2, e3, e4}));
        series.add(new ColumnSeriesBean("Warning", new double[] {0, 0, 0, 0, w1, w2, w3, w4}));
        series.add(new ColumnSeriesBean("Normal", new double[] {temp1 - temp2, temp3 - temp4, temp5 - temp6, temp7 - temp8, n1, n2, n3, n4}));


        return new ColumnDataBean("container4", "Error Column", series);
    }
}
