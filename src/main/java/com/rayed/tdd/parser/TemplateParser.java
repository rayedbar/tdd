package com.rayed.tdd.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author rayed
 * @since August 12, 2018
 */
public class TemplateParser {

    public List<String> parse(String template) {
        List<String> segments = new ArrayList<>();

        int index = collectSegments(segments, template);

        addTail(segments, template, index);
        addEmptyStringIfTemplateIsEmpty(segments);

        return segments;
    }

    private int collectSegments(List<String> segments, String template) {
        String regex = "\\$\\{[^}]*?\\}";
        Matcher matcher = Pattern.compile(regex).matcher(template);

        int index = 0;
        while (matcher.find()) {
            addPrecedingText(segments, template, matcher, index);
            addVariable(segments, template, matcher);
            index = matcher.end();
        }

        return index;
    }

    private void addPrecedingText(List<String> segments, String template, Matcher matcher, int index) {
        if (index != matcher.start()) {
            segments.add(template.substring(index, matcher.start()));
        }
    }

    private void addVariable(List<String> segments, String template, Matcher matcher) {
        segments.add(template.substring(matcher.start(), matcher.end()));
    }

    private void addTail(List<String> segments, String template, int index) {
        if (index < template.length()) {
            segments.add(template.substring(index));
        }
    }

    private void addEmptyStringIfTemplateIsEmpty(List<String> segments) {
        if (segments.isEmpty()) {
            segments.add("");
        }
    }
}
