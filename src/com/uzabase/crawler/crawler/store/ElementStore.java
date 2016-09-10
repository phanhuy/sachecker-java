package com.uzabase.crawler.crawler.store;

/**
 * Created by Trung on 1/23/2016 3:13 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class ElementStore {

    public static final String ACCESSKEY_SELECTOR = "[accesskey]";
    public static final String ACTIVETEXT_SELECTOR = "marquee, blink";
    public static final String ARIA_ACTIVE_DESCENDANT = "[aria-activedescendant]";
    public static final String BUTTON_SELECTOR = "button";
    public static final String ONCLICK_SELECTOR = "[onclick]";
    public static final String EVENT_ELEMENT_SELECTOR = "[onclick], " +
            "[ondblclick], [onselect], [onchange], [onsubmit], [onreset], " +
            "[onkeyup], [onkeydown], [onkeypress], [onmousedown], " +
            "[onmouseup], [onmousemove], [onmouseout], [onmouseover], " +
            "[onfocus], [onblur]";
    public static final String FIELDSET_SELECT = "fieldset";
    public static final String FORM_CONTROL_SELECT = "input[type=button], " +
            "input[type=submit], input[type=reset]";
    public static final String FRAME_SELECTOR = "frame, iframe";
    public static final String HEAD_SELECT = "head";
    public static final String HEADING_SELECT = "h1, h2, h3, h4, h5, h6";
    public static final String HTML_SELECT = "html";
    public static final String IMG = "img";
    public static final String INPUT_CONTROL_SELECT = "input[type=checkbox], " +
            "input[type=file], input[type=password], " +
            "input[type=image], input[type=radio], input[type=text], " +
            "select, textarea";
    public static final String LINK_SELECT = "a";
    public static final String MOUSEDOWN_SELECTOR = "[mousedown]";
    public static final String MOUSE_EVENT_SELECTOR = "[onclick], " +
            "[onmousedown], [onmouseup], [onmousemove], [onmouseout], " +
            "[onmouseover]";
    public static final String MOUSEOUT_SELECTOR = "[mouseout]";
    public static final String MOUSEOVER_SELECTOR = "[mouseover]";
    public static final String MOUSEUP_SELECTOR = "[mouseup]";
    public static final String SELECT = "select";
    public static final String STYLE_SELECT = "font, b, i, u";
    public static final String SUMMARY_SELECT = "label, legend";
    public static final String TABLE = "table";
    public static final String TITLE_SELECT = "title";

}
