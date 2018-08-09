package com.rayed.tdd.template;

import com.rayed.tdd.exceptions.MissingValueException;

import java.util.HashMap;
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
        String result = substituteVariablesWithValues();
        checkForMissingValues(result);
        return result;
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
        Matcher matcher = Pattern.compile("\\$\\{.+\\}").matcher(result);
        if (matcher.find()) {
            throw new MissingValueException("No value for " + matcher.group());
        }
    }
}
