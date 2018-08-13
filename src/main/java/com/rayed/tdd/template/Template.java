package com.rayed.tdd.template;

import com.rayed.tdd.exceptions.MissingValueException;
import com.rayed.tdd.parser.TemplateParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rayed
 * @since August 08, 2018
 */
public class Template {

    private String templateText;
    private Map<String, String> templateVariables;

    public static boolean isVariable(String segment) {
        return segment.startsWith("${") && segment.endsWith("}");
    }

    public Template(String templateText) {
        this.templateText = templateText;
        this.templateVariables = new HashMap<>();
    }

    public void set(String variable, String value) {
        this.templateVariables.put(variable, value);
    }

    public String evaluate() {
        TemplateParser parser = new TemplateParser();
        List<String> segments = parser.parse(templateText);
        return concatenate(segments);
    }

    private String concatenate(List<String> segments) {
        StringBuilder result = new StringBuilder();
        for (String segment : segments) {
            append(segment, result);
        }
        return result.toString();
    }

    private void append(String segment, StringBuilder result) {
        if (isVariable(segment)) {
            evaluateVariable(segment, result);
        } else {
            result.append(segment);
        }
    }

    private void evaluateVariable(String segment, StringBuilder result) {
        String key = segment.substring(2, segment.length() - 1);
        if (!templateVariables.containsKey(key)) {
            throw new MissingValueException("No value for " + segment);
        }
        result.append(templateVariables.get(key));
    }
}
