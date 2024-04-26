package com.garden.alanni.retry.exception;

import com.garden.alanni.retry.RetryUtils;

/**
 * 用于标记 实现了此接口的异常情况 将不进行重试操作 {@link com.garden.alanni.retry.RetryUtils#performRetryableOperation(RetryUtils.RetryableOperation)}
 * @author 吴宇伦
 */
public interface UnRetryable {
}
