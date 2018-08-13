package com.rayed.tdd.segments;

import com.rayed.tdd.exceptions.MissingValueException;

import java.util.Map;

/**
 * @author rayed
 * @since August 13, 2018
 */
public class Variable implements Segment {

    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String evaluate(Map<String, String> templateVariables) {
        if (!templateVariables.containsKey(name)) {
            throw new MissingValueException("No value for ${" + name + "}");
        }
        return templateVariables.get(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Variable variable = (Variable) o;

        if (!name.equals(variable.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
