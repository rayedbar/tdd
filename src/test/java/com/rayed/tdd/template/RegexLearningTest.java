package com.rayed.tdd.template;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author rayed
 * @since August 12, 2018
 */
public class RegexLearningTest {

    @Test
    public void howGroupCountWorksTest() {
        String haystack = "The needle shop sells needles";
        String regex = "(needle)";
        Matcher matcher = Pattern.compile(regex).matcher(haystack);
        assertEquals(1, matcher.groupCount());
    }

    @Test
    public void startAndEndTest() {
        String haystack = "The needle shop sells needles";
        String regex = "needle";

        Matcher matcher = Pattern.compile(regex).matcher(haystack);

        assertTrue(matcher.find());
        assertEquals(4, matcher.start(), "Wrong start index of first match!");
        assertEquals(10, matcher.end(), "Wrong end index of first match!");

        assertTrue(matcher.find());
        assertEquals(22, matcher.start(), "Wrong start index of second match!");
        assertEquals(28, matcher.end(), "Wrong end index of second match!");

        assertFalse(matcher.find(), "Should not find any more matches!");
    }
}
