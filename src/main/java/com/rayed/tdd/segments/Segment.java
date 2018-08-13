package com.rayed.tdd.segments;

import java.util.Map;

/**
 * @author rayed
 * @since August 13, 2018
 */
public interface Segment {

    String evaluate(Map<String, String> variables);
}
