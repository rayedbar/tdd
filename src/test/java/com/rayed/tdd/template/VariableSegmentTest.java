package com.rayed.tdd.template;

import com.rayed.tdd.exceptions.MissingValueException;
import com.rayed.tdd.segments.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author rayed
 * @since August 13, 2018
 */
public class VariableSegmentTest {

    Map<String, String> variables;

    @BeforeEach
    public void setUp() {
        variables = new HashMap<>();
    }

    @Test
    public void variableEvaluatesToItsValue() {
        String myVariable = "myVariable";
        String myValue = "myValue";

        variables.put(myVariable, myValue);

        assertEquals(myValue, new Variable(myVariable).evaluate(variables));
    }

    @Test
    public void missingValueRaisesException() {
        String myVariable = "myVariable";
        try {
            new Variable(myVariable).evaluate(variables);
            fail("Missing variable value should throw an exception");
        } catch (MissingValueException expected) {
            assertEquals("No value for ${" + myVariable + "}", expected.getMessage());
        }
    }
}
