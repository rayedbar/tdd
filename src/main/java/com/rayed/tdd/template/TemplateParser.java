package com.rayed.tdd.template;

import com.rayed.tdd.template.PlainText;
import com.rayed.tdd.template.Segment;
import com.rayed.tdd.template.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author rayed
 * @since August 12, 2018
 */
public class TemplateParser {

    public List<Segment> parse(String template) {
        List<Segment> segments = new ArrayList<>();

        int index = collectSegments(segments, template);
        addTail(segments, template, index);
        addEmptyStringIfTemplateIsEmpty(segments);

        return segments;
    }

    private int collectSegments(List<Segment> segments, String template) {
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

    private void addPrecedingText(List<Segment> segments, String template, Matcher matcher, int index) {
        if (index != matcher.start()) {
            segments.add(new PlainText(template.substring(index, matcher.start())));
        }
    }

    private void addVariable(List<Segment> segments, String template, Matcher matcher) {
        segments.add(new Variable(template.substring(matcher.start() + 2, matcher.end() - 1)));
    }

    private void addTail(List<Segment> segments, String template, int index) {
        if (index < template.length()) {
            segments.add(new PlainText(template.substring(index)));
        }
    }

    private void addEmptyStringIfTemplateIsEmpty(List<Segment> segments) {
        if (segments.isEmpty()) {
            segments.add(new PlainText(""));
        }
    }
}
