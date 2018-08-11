package com.rayed.tdd.template;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author rayed
 * @since August 11, 2018
 */
public class TemplatePerformanceTest {

    private Template template;

    @BeforeEach
    public void setUp() {
        buildTemplate();
        populateTemplate();
    }

    private void buildTemplate() {
        StringBuffer text = new StringBuffer(10000);
        for (int i = 0, var = 1; i < 100; i++) {
            text.append(" template ");
            if (i % 5 == 0) {
                text.append("${var").append(var).append("}");
                var = var + 1;
            }
        }
        template = new Template(text.toString());
    }

    private void populateTemplate() {
        for (int var = 1; var < 21; var++) {
            template.set("var" + var, "value of var" + var);
        }
    }

    @Test
    public void templateWith1000WordsAnd20Variables() {
        long expected = 200L;
        long time = System.currentTimeMillis();
        String result = template.evaluate();
        time = System.currentTimeMillis() - time;
        assertTrue(time <= expected, "Rendering the template took " + time + "ms while the target was " +
                expected + "ms");
    }


}
