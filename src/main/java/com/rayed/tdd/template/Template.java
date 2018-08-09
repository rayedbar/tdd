package com.rayed.tdd.template;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rayed
 * @since August 08, 2018
 */
public class Template {

    private String templateText;
    private Map<String, String> variables;

    public Template(String templateText) {
        this.templateText = templateText;
        this.variables = new HashMap<String, String>();
    }

    public void set(String variable, String value) {
        this.variables.put(variable, value);
    }

    public String evaluate() {
        String result = templateText;
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            String regex = "\\$\\{" + entry.getKey() + "\\}";
            result = result.replaceAll(regex, entry.getValue());
        }
        return result;
    }
}
