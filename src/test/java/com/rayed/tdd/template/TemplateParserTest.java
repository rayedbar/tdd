package com.rayed.tdd.template;

import com.rayed.tdd.parser.TemplateParser;
import com.rayed.tdd.segments.PlainText;
import com.rayed.tdd.segments.Segment;
import com.rayed.tdd.segments.Variable;
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
    public void emptyTemplateRendersAsEmptyString() {
        List<String> segments = parse("");
        assertSegments(segments, "");
    }

    @Test
    public void templateWithOnlyPlainText() {
        List<String> segments = parse("plain text only");
        assertSegments(segments, "plain text only");
    }

    @Test
    public void parseMultipleVariables() {
        List<String> segments = parse("${a}:${b}:${c}");
        assertSegments(segments, "${a}", ":", "${b}", ":", "${c}");
    }

    @Test
    public void parseTemplateIntoSegmentObjects() {
        TemplateParser parser = new TemplateParser();
        List<Segment> segments = parser.parseSegments("a ${b} c ${d}");
        assertSegments(segments, new PlainText("a "), new Variable("b"), new PlainText(" c "), new Variable("d"));
    }

    private List<String> parse(String template) {
        return new TemplateParser().parse(template);
    }

    private void assertSegments(List<?> actual, Object... expected) {
        assertEquals(expected.length, actual.size(), "Number of segments do not match!");
        assertEquals(Arrays.asList(expected), actual);
    }
}
