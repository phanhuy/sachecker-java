package com.uzabase.accessibility.checker;

import com.uzabase.accessibility.checker.service.Issue;
import com.uzabase.accessibility.checker.service.Rule;
import com.uzabase.accessibility.checker.utils.RuleResultCounter;
import com.uzabase.accessibility.checker.utils.RuleType;
import com.uzabase.accessibility.checker.wcag.operable.*;
import com.uzabase.accessibility.checker.wcag.perceivable.*;
import com.uzabase.accessibility.checker.wcag.robust.StylingElementNotUsed;
import com.uzabase.accessibility.checker.wcag.understandable.*;
import com.uzabase.crawler.crawler.Filter;
import org.jsoup.nodes.Element;

import java.util.*;

import static com.uzabase.accessibility.checker.utils.RuleType.*;

/**
 * Created by Trung on 1/30/2016 2:30 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class Evaluator {

    public static Set<Rule> rules = new HashSet<>();

    public static Map<Rule, RuleResultCounter> rulesOperable = new HashMap<> ();
    public static Map<Rule, RuleResultCounter> rulesPerceivable = new HashMap<> ();
    public static Map<Rule, RuleResultCounter> rulesRobust = new HashMap<> ();
    public static Map<Rule, RuleResultCounter> rulesUnderstandable = new HashMap<> ();

    public Evaluator() {
        rulesOperable.clear();
        rulesPerceivable.clear();
        rulesRobust.clear();
        rulesUnderstandable.clear();
    }

    private int elementsChecked = 0;

    public int getElementsChecked() {
        return elementsChecked;
    }

    /**
     * Add a package containing rule implementations to the evaluator. Every
     * class implementing the Rule interface in the given package will be added
     * to the evaluator.
     *
     * @param packageName of package name in the current classpath to add.
     */
    public void addPackage(String packageName) {
        addRule(new AccessKeyValueUnique(), OPERABLE);
        addRule(new ActiveTextElementNotPresent(), OPERABLE);
        addRule(new FrameHasTitle(), OPERABLE);
        addRule(new HeadingHasText(), OPERABLE);
        addRule(new IeReservedAccessKeyValueNotUsed(), OPERABLE);
        addRule(new LinkTextNotReplicated(), OPERABLE);
        addRule(new MouseDownEventHasKeyEquivalent(), OPERABLE);
        addRule(new MouseEventHasKeyboardEvent(), OPERABLE);
        addRule(new MouseUpEventHaskeyEquivalent(), OPERABLE);
        addRule(new NonInteractiveElementWithEventHasRole(), OPERABLE);
        addRule(new OnClickIsFocusable(), OPERABLE);
        addRule(new OnMouseOutAndOnBlur(), OPERABLE);
        addRule(new OnMouseOverAndOnFocus(), OPERABLE);
        addRule(new SelectNotOnChange(), OPERABLE);
        addRule(new TitleHasEnoughWord(), OPERABLE);
        addRule(new TitleIsConcise(), OPERABLE);
        addRule(new TitleIsNotEmpty(), OPERABLE);

        addRule(new AltTextLengthReasonable(), PERCEIVABLE);
        addRule(new AltTextOnImage(), PERCEIVABLE);
        addRule(new AltTextOnImageNotBad(), PERCEIVABLE);
        addRule(new ComplexTableDataHasHeading(), PERCEIVABLE);
        addRule(new ComplexTableHeadingHasId(), PERCEIVABLE);
        addRule(new ComplexTableHeadingIdUnique(), PERCEIVABLE);
        addRule(new InvisibleImageNoTitle(), PERCEIVABLE);
        addRule(new TableHasHeadings(), PERCEIVABLE);
        addRule(new TableHasSummaryAttribute(), PERCEIVABLE);
        addRule(new TableSummaryUnique(), PERCEIVABLE);

        addRule(new StylingElementNotUsed(), ROBUST);

        addRule(new ButtonHasContent(), UNDERSTANDABLE);
        addRule(new ControlHasDescriptor(), UNDERSTANDABLE);
        addRule(new ControlIdUnique(), UNDERSTANDABLE);
        addRule(new DescriptionHasText(), UNDERSTANDABLE);
        addRule(new FieldSetHasLegend(), UNDERSTANDABLE);
        addRule(new FormControlHasDescription(), UNDERSTANDABLE);
        addRule(new HtmlHasValidLanguageCode(), UNDERSTANDABLE);
        addRule(new ImageInputHasDescription(), UNDERSTANDABLE);

//        Reflections reflection = new  Reflections(packageName);
//        Set<Class<? extends Rule>> classes =
//                reflection.getSubTypesOf(Rule.class);
//        System.out.println(classes.size());
//        for (Class<? extends Rule> rule : classes) {
//            if (Modifier.isAbstract(rule.getModifiers())) {
//                continue;
//            }
//            try {
//                Rule instance = rule.newInstance();
//                addRule(instance);
//            } catch (InstantiationException e) {
//                throw new RuntimeException(e);
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
    }

    /**
     * Add a single rule implementation to the evaluator.
     *
     * @param rule to add to the evaluator.
     */
    public void addRule(Rule rule, RuleType type) {
        rules.add(rule);
        switch (type) {
            case OPERABLE:
                rulesOperable.put(rule, new RuleResultCounter());
                break;
            case PERCEIVABLE:
                rulesPerceivable.put(rule, new RuleResultCounter());
                break;
            case ROBUST:
                rulesRobust.put(rule, new RuleResultCounter());
                break;
            case UNDERSTANDABLE:
                rulesUnderstandable.put(rule, new RuleResultCounter());
                break;
        }
    }

    /**
     * Collect the issues for the current loaded set of
     * rules
     * @param root element for analysis.
     * @return the collection of issues identified.
     */
    public List<Issue> collectIssues(Element root) {
        List<Issue> result = new ArrayList<>();
        elementsChecked = 0;
        for (Rule rule : rules) {
            Filter filter = rule.getFilter();
            for (Element target : filter.result(root)) {
                Issue issue = rule.check(target);
                elementsChecked++;

                if (rulesOperable.containsKey(rule))
                    rulesOperable.get(rule).increaseTotal();
                if (rulesPerceivable.containsKey(rule))
                    rulesPerceivable.get(rule).increaseTotal();
                if (rulesRobust.containsKey(rule))
                    rulesRobust.get(rule).increaseTotal();
                if (rulesUnderstandable.containsKey(rule))
                    rulesUnderstandable.get(rule).increaseTotal();

                if (null != issue) {
                    result.add(issue);
                    if (rulesOperable.containsKey(rule))
                        rulesOperable.get(rule).increaseError();
                    if (rulesPerceivable.containsKey(rule))
                        rulesPerceivable.get(rule).increaseError();
                    if (rulesRobust.containsKey(rule))
                        rulesRobust.get(rule).increaseError();
                    if (rulesUnderstandable.containsKey(rule))
                        rulesUnderstandable.get(rule).increaseError();
                }
            }
        }
        return result;
    }
}
