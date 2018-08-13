package com.rayed.tdd.parser;

import com.rayed.tdd.segments.PlainText;
import com.rayed.tdd.segments.Segment;
import com.rayed.tdd.segments.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author rayed
 * @since August 12, 2018
 */
public class TemplateParser {

    public List<Segment> parseSegments(String template) {
        List<Segment> segments = new ArrayList<>();
        List<String> segmentStrings = parse(template);

        for (String segmentString : segmentStrings) {
            if (isVariable(segmentString)) {
                segments.add(new Variable(segmentString.substring(2, segmentString.length() - 1)));
            } else {
                segments.add(new PlainText(segmentString));
            }
        }

        return segments;
    }

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

    private boolean isVariable(String segment) {
        return segment.startsWith("${") && segment.endsWith("}");
    }
}
