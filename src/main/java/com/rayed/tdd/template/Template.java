package com.rayed.tdd.template;

import com.rayed.tdd.exceptions.MissingValueException;
import com.rayed.tdd.parser.TemplateParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author rayed
 * @since August 08, 2018
 */
public class Template {

    private String templateText;
    private Map<String, String> variables;

    public Template(String templateText) {
        this.templateText = templateText;
        this.variables = new HashMap<>();
    }

    public void set(String variable, String value) {
        this.variables.put(variable, value);
    }

    public String evaluate() {
        TemplateParser parser = new TemplateParser();
        List<String> segments = parser.parse(templateText);

        StringBuilder result = new StringBuilder();
        for (String segment : segments) {
            append(segment, result);
        }

        return result.toString();
    }

    private void append(String segment, StringBuilder result) {
        if (segment.startsWith("${") && segment.endsWith("}")) {
            String key = segment.substring(2, segment.length() - 1);
            if (!variables.containsKey(key)) {
                throw new MissingValueException("No value for " + segment);
            }
            result.append(variables.get(key));
        } else {
            result.append(segment);
        }
    }

    private String substituteVariablesWithValues() {
        String result = templateText;
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            String regex = "\\$\\{" + entry.getKey() + "\\}";
            result = result.replaceAll(regex, entry.getValue());
        }
        return result;
    }

    private void checkForMissingValues(String result) {
        Matcher matcher = Pattern.compile("\\$\\{.+?\\}").matcher(result);
        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        if (!matches.isEmpty()) {
            throw new MissingValueException("No value for " + String.join(", ", matches));
        }
    }
}
