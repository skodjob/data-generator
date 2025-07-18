/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator;

/**
 * Exception for DataGenerator errors mainly during output serialization
 */
public final class DataGeneratorException extends RuntimeException {
    /**
     * Exception for DataGenerator errors mainly during output serialization
     *
     * @param msg   message that will be printed
     * @param cause cause of the exception
     */
    public DataGeneratorException(String msg, Throwable cause) {
        super(msg, cause);
    }
}