package com.garden.alanni.retry.exception;

/**
 * @author 吴宇伦
 */
public class UnRetryableException extends RuntimeException implements UnRetryable {
    public UnRetryableException() {
    }

    public UnRetryableException(String message) {
        super(message);
    }

    public UnRetryableException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnRetryableException(Throwable cause) {
        super(cause);
    }

    public UnRetryableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
