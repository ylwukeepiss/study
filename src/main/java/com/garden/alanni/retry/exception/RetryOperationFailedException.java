package com.garden.alanni.retry.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 可重试操作执行失败时抛出的异常
 * @author 吴宇伦
 */
public class RetryOperationFailedException extends RuntimeException {
    private static final String DELIVERY_INFO_UPDATE_FAIL_TEMPLATE = "（已重试%d次，每一次的异常依次为：%s）";

    private List<String> errMessages = Collections.emptyList();


    public RetryOperationFailedException() {
    }

    public RetryOperationFailedException(String message) {
        super(message);
    }

    public RetryOperationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RetryOperationFailedException(Throwable cause) {
        super(cause);
    }

    public RetryOperationFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public List<String> getErrMessages() {
        return errMessages;
    }

    public void setErrMessages(List<String> errMessages) {
        this.errMessages = errMessages;
    }

    public String getFormatErrMessages() {
        String reason = "";
        if (!CollectionUtils.isEmpty(errMessages)) {
            reason = String.format(DELIVERY_INFO_UPDATE_FAIL_TEMPLATE, errMessages.size(), StringUtils.join(errMessages, ";"));
        }
        return reason;
    }
}
