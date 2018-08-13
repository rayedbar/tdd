package com.rayed.tdd.template;

import com.rayed.tdd.segments.PlainText;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rayed
 * @since August 13, 2018
 */
public class PlainTextSegmentTest {

    @Test
    public void plainTextEvaluatesTextAsIs() throws Exception {
        String text = "abc def";
        Map<String, String> variables = new HashMap<>();
        Assertions.assertEquals(text, new PlainText(text).evaluate(variables));
    }
}
