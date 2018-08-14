package com.rayed.tdd.template;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author rayed
 * @since August 12, 2018
 */
public class TemplateParserTest {

    @Test
    public void parseTemplateIntoSegmentObjects() {
        TemplateParser parser = new TemplateParser();
        List<Segment> segments = parser.parse("a ${b} c ${d}");
        assertSegments(segments, new PlainText("a "), new Variable("b"), new PlainText(" c "), new Variable("d"));
    }

    private void assertSegments(List<?> actual, Object... expected) {
        assertEquals(expected.length, actual.size(), "Number of segments do not match!");
        assertEquals(Arrays.asList(expected), actual);
    }
}
