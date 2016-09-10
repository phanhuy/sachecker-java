package com.uzabase.accessibility.checker.wcag;

import com.uzabase.accessibility.checker.utils.Role;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Trung on 1/29/2016 1:33 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public abstract class Shared {

    public static final String ACCESS_KEY = "accesskey";
    public static final String ALT_TEXT = "alt";
    public static final String ANCHOR = "a";
    public static final String ARIA_PRESENTATION = "presentation";
    public static final String ARIA_ROLE = "role";
    public static final String BLUR = "onblur";
    public static final String BUTTON = "button";
    public static final String COLSPAN = "colspan";
    public static final String FOCUS = "onfocus";
    public static final String HEAD = "head";
    public static final String HEADERS = "headers";
    public static final String HREF = "href";
    public static final String ID = "id";
    public static final String IMAGE = "image";
    public static final String INPUT = "input";
    public static final String KEY_DOWN = "onkeydown";
    public static final String KEY_PRESS = "onkeypress";
    public static final String KEY_UP = "onkeyup";
    public static final String LANG = "lang";
    public static final String LEGEND = "legend";
    public static final String ON_CHANGE = "onchange";
    public static final String ROLE = "role";
    public static final String ROWSPAN = "rowspan";
    public static final String SELECT = "select";
    public static final String SUMMARY = "summary";
    public static final String TAB_INDEX = "tabindex";
    public static final String TD = "td";
    public static final String TH = "th";
    public static final String TITLE = "title";
    public static final String TYPE = "type";
    public static final String VALUE = "value";

    /**
     * Get the root element by navigating to the top parent
     *
     * @param element element starting point
     * @return the root element or the passed element if no parents
     */
    public static Element getRootElement(Element element) {
        Elements parents = element.parents();

        if (parents.isEmpty())
            return element;
        else return parents.last();
    }

    public static String containedText(Element root) {
        StringBuilder builder = new StringBuilder();

        recurseNode(root, builder);
        return builder.toString().trim().replace("\\W+", " ");

    }

    /**
     * Extract the contained text from text and alt nodes
     *
     * @param node root node to traverse from
     * @param builder String with white space normalised as single spaces
     */
    private static void recurseNode(Node node, StringBuilder builder) {
        if (node instanceof TextNode) {
            builder.append(((TextNode)node).text());
            builder.append(" ");
            return;
        }
        // check for title text
        if (node.hasAttr(TITLE)) {
            builder.append(node.attr(TITLE));
            builder.append(" ");
        }

        // if the current node has alt text append it
        if (node.hasAttr(ALT_TEXT)) {
            builder.append(ALT_TEXT);
            // add trailing white space to separate elements
            builder.append(" ");
        }

        // recurse into the child nodes
        for (Node child : node.childNodes()) {
            recurseNode(child, builder);
        }
    }

    public boolean hasAriaRole(Element element) {
        if (!element.hasAttr(ROLE))
            return false;

        for (Role role : Role.values())
            if (element.attr(ROLE).contains(role.toString()))
                return true;

        return false;
    }

    public static boolean notFocusable(Element element) {
        List<String> focusable = Arrays.asList("a", "button", "input", "select", "textarea");

        if (focusable.contains(element.tagName())) {
            if (element.hasAttr(TAB_INDEX) &&
                    -1 >= Integer.parseInt(element.attr(TAB_INDEX)))
                return true;
        }

        return false;
    }

    /**
     * Check if an elements is visible based on whether it has and aria presentation tag
     *
     * @param element
     * @return true if the element is visible rather than just presentation
     */
    public static boolean isVisible(Element element) {
        Attributes attributes = element.attributes();

        if (attributes.hasKey("role")){
            if (attributes.get(ARIA_ROLE).equals(ARIA_PRESENTATION))
                return false;
            else return true;
        } else return true;
    }

    /**
     * Check if a HTML table is not complex. Typically a complex table has at
     * least one cell which is a multi column or multi row. In those cases it
     * is required that the table use heading id alignment to aid screen
     * readers.
     *
     * @param table to check
     * @return true if the table is not complex
     */
    public static boolean notComplexTable(Element table) {
        for (Element cell : table.select(TH + "," + TD)) {
            if (cell.hasAttr(COLSPAN) || cell.hasAttr(ROWSPAN))
                return false;
        }
        return true;
    }

    /**
     * Check if an HTML input is an image input type
     *
     * @param element
     * @return
     */
    public static boolean isImageInput(Element element) {
        return element.nodeName().equals(INPUT) &&
                element.hasAttr(TYPE) &&
                element.attr(TYPE).equals(IMAGE);
    }
}
