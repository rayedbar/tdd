package com.rayed.tdd.template;

import java.util.Map;

/**
 * @author rayed
 * @since August 13, 2018
 */
public class PlainText implements Segment {

    private String text;

    public PlainText(String text) {
        this.text = text;
    }

    @Override
    public String evaluate(Map<String, String> variables) {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlainText plainText = (PlainText) o;

        if (!text.equals(plainText.text)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}
