package com.rayed.tdd.template;

/**
 * @author rayed
 * @since August 09, 2018
 */
public class MissingValueException extends RuntimeException {

    public MissingValueException(String message) {
        super(message);
    }
}
