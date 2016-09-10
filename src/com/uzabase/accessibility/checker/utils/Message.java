package com.uzabase.accessibility.checker.utils;

/**
 * Rule for on click elements also being focusable
 *
 * Created by Trung on 1/29/2016 2:50 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class Message {

    public static final String ACCESS_KEY_VALUE_UNIQUE = "Check that access key attribute elements use a unique key id";
    public static final String ACTIVE_TEXT_ELEMENT_NOT_PRESENT = "Check that active text elements like marqee and blink are not present ";
    public static final String ALT_TEXT_LENGTH_REASONABLE = "Check that visible images use good alt text length";
    public static final String ALT_TEXT_ON_IMAGE = "Check that visible images have alt text";
    public static final String ALT_TEXT_ON_IMAGE_NOT_BAD = "Find elements that have an invalid alt text value";
    public static final String BUTTON_HAS_CONTENT = "Check that button elements have content";
    public static final String COMPLEX_TABLE_DATA_HAS_HEADING = "Check that complex tables have heading id reference on data elements";
    public static final String  COMPLEX_TABLE_HEADING_HAS_ID = "Check that complex tables have id attributes on table heading element";
    public static final String COMPLEX_TABLE_HEADING_ID_UNIQUE = "Check that complex tables have unique ids on table headings";
    public static final String CONTROL_HAS_DESCRIPTOR = "Check that input control elements have a for label";
    public static final String CONTROL_ID_UNIQUE = "Check that input controls have unique id";
    public static final String DESCRIPTION_HAS_TEXT = "Check that label or legend element has text content";
    public static final String FILED_SET_HAS_LEGEND = "Check that field set element has a legend";
    public static final String FORM_CONTROL_HAS_DESCRIPTION = "Check that form controls have a title or a value";
    public static final String FRAME_HAS_TITLE = "Check that frame elements define a title attribute";
    public static final String FRAME_TITLE_UNIQUE = "Check that a frame title is unique";
    public static final String HEADING_HAS_TEXT = "Check that heading elements have text";
    public static final String HTML_HAS_VALID_LANGUAGE_CODE = "Check that the html element has a valid language code";
    public static final String IE_RESERVED_ACCESS_KEY_VALUE_NOT_USED = "Check that access key attribute values are not already used by internet explorer";
    public static final String IMAGE_INPUT_HAS_DESCRIPTION = "Check that image input element has either alt or title";
    public static final String INVISIBLE_IMAGE_NO_TITLE = "Check that an image is not visible it does not have a title";
    public static final String LINK_TEXT_NOT_REPLICATED = "Check that there are not two links with the same text but different href url";
    public static final String MOUSE_DOWN_EVENT_HAS_KEY_EQUIVALENT = "Check that mouse down has equivalent key event";
    public static final String MOUSE_EVENT_HAS_KEYBOARD_EVENT = "Check that elements have mouse actions also have keyboard actions";
    public static final String MOUSE_UP_EVENT_HAS_KEY_EQUIVALENT = "Check that mouse up has equivalent key event";
    public static final String NON_INTERACTIVE_ELEMENT_WITH_EVENT_HAS_ROLE = "Check that non interactive elements have an aria role if they use events";
    public static final String ON_CLINK_IS_FOCUSABLE = "Check that when an element has onclick is also focusable";
    public static final String ON_MOUSE_OUT_AND_ON_BLUR = "Check that when an element has a mouse out also has on blur";
    public static final String ON_MOUSE_OVER_AND_ON_FOCUS = "Check that when an element has mouse over it also has on focus";
    public static final String SELECT_NOT_ON_CHANGE = "Check that select element doesn't use on change";
    public static final String STYLING_ELEMENT_NOT_USED = "Check that style elements are not used";
    public static final String TABLE_HAS_HEADINGS = "Check that data table has a at least on heading";
    public static final String TABLE_HAS_SUMMARY_ATTRIBUTE = "Check that data table has a summary attribute";
    public static final String TABLE_SUMMARY_UNIQUE = "Check that table summary is unique";
    public static final String TITLE_HAS_ENOUGH_WORD = "Check that the title element text has more than one word";
    public static final String TITLE_IS_CONCISE = "Check that the title element text is concise";
    public static final String TITLE_IS_NOT_EMPTY = "Check that the page has a title and it has text";
}
