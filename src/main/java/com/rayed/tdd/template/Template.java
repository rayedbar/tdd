package com.rayed.tdd.template;

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

    public Template(String templateText) {
        this.templateText = templateText;
        this.templateVariables = new HashMap<>();
    }

    public void set(String variable, String value) {
        this.templateVariables.put(variable, value);
    }

    public String evaluate() {
        TemplateParser parser = new TemplateParser();
        List<Segment> segments = parser.parseSegments(templateText);
        return concatenate(segments);
    }

    private String concatenate(List<Segment> segments) {
        StringBuilder result = new StringBuilder();
        for (Segment segment : segments) {
            result.append(segment.evaluate(templateVariables));
        }
        return result.toString();
    }
}
