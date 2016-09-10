package com.uzabase.accessibility.checker;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.Rule;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Trung on 1/21/2016 4:42 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class App {
    private static final String HTML = "<html>" +
            "<head><title>Some things are just blah</title></head>" +
            "<body>" +
            "<a accesskey=y>FirstLink</a>" +
            "<div onclick=\"alert('hi')\">eat me</div>" +
            "<div onclick=\"alert('hi')\">drink me</div>" +
            "<a accesskey=r>SecondLink</a>" +
            "<div ondblclick=\"alert('badness');\">Interact</div>" +
            "<div onkeyup=\"alert('badness');\">Interact</div>" +
            "<div onkeydown=\"alert('badness');\">Interact</div>" +
            "<div onkeypress=\"alert('badness');\">Interact</div>" +
            "<div onmousedown=\"alert('badness');\">Interact</div>" +
            "<div onmouseup=\"alert('badness');\">Interact</div>" +
            "<div onmousemove=\"alert('badness');\">Interact</div>" +
            "<div onmouseout=\"alert('badness');\">Interact</div>" +
            "<div onmouseover=\"alert('badness');\">Interact</div>" +
            "<div onselect=\"alert('badness');\">Interact</div>" +
            "<div onchange=\"alert('badness');\">Interact</div>" +
            "<div onsubmit=\"alert('badness');\">Interact</div>" +
            "<div onreset=\"alert('badness');\">Interact</div>" +
            "<div onfocus=\"alert('badness');\">Interact</div>" +
            "<div onblur=\"alert('badness');\">Interact</div>" +
            "<form action=\"/\"><input type=submit />" +
            "<fieldset>" +
            "<input type=\"button\" />" +
            "<textarea>text</textarea>" +
            "</fieldset>" +
            "<input type=\"reset\" />" +
            "<button>click me</button>" +
            "</form>" +
            "<blink>I'm going to flash</blink>" +
            "<marquee>I'm going to scroll, classy eh?</marquee>" +
            "<iframe src=\"http://www.google.com/\" />" +
            "<frame src=\"http://www.google.com/\" />" +
            "<h1>Heading 1</h1>" +
            "<h2>Heading 2</h2>" +
            "<h3>Heading 3</h3>" +
            "<h4>Heading 4</h4>" +
            "<img src=\"/nowhere.gif\" />" +
            "<select><option>a</option><option>b</option></select>" +
            "<table><tr><td>I'm a cell</td></tr></table>" +
            "</body></html>";
    private static final int TIMEOUT = 20000;

    public static void main(String[] args) throws Exception {

        Document document;
        try {
            document = Jsoup.parse(new URL("http://vectv.net/"), TIMEOUT);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error due to malformed URL parameter", e);
        } catch (IOException e) {
            throw new RuntimeException("IO Exception fetching page.", e);
        }

        //System.out.println(document.toString());
        System.out.println("*************************************");
        Evaluator evaluator = new Evaluator();
        evaluator.addPackage("com.uzabase.accessibility.checker.wcag.robust");
        System.out.println(Evaluator.rules.size());
        List<Issue> result = evaluator.collectIssues(document);

//        for (Issue issue : result) {
//            System.out.println(issue.toString());
//            System.out.println("*************************************");
//        }
        System.out.println("*************************************");

        for (Rule rule : Evaluator.rulesOperable.keySet()) {
            System.out.println(rule.getRuleName() + " = " + Evaluator.rulesOperable.get(rule).toString());
        }
        System.out.println("*************************************");
        for (Rule rule : Evaluator.rulesPerceivable.keySet()) {
            System.out.println(rule.getRuleName() + " = " + Evaluator.rulesPerceivable.get(rule).toString());
        }
        System.out.println("*************************************");
        for (Rule rule : Evaluator.rulesRobust.keySet()) {
            System.out.println(rule.getRuleName() + " = " + Evaluator.rulesRobust.get(rule).toString());
        }
        System.out.println("*************************************");
        for (Rule rule : Evaluator.rulesUnderstandable.keySet()) {
            System.out.println(rule.getRuleName() + " = " + Evaluator.rulesUnderstandable.get(rule).toString());
        }

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
        System.out.println(evaluator.getElementsChecked());

        System.out.println(temp1 + ", " + temp2);
        System.out.println(temp3 + ", " + temp4);
        System.out.println(temp5 + ", " + temp6);
        System.out.println(temp7 + ", " + temp8);
    }
}
