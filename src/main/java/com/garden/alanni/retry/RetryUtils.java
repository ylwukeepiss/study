package com.garden.alanni.retry;

import com.garden.alanni.retry.exception.RetryOperationFailedException;
import com.garden.alanni.retry.exception.UnRetryable;
import com.garden.alanni.retry.exception.UnRetryableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.listener.RetryListenerSupport;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.policy.TimeoutRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * 当需要在线程中发起立即重试时 可以使用该工具
 * @author 吴宇伦
 */
public class RetryUtils {
    private static final Logger log = LoggerFactory.getLogger(RetryUtils.class);
    public static final int DEFAULT_NUM_RETRIES = 3;

    /**
     * 可重试接口
     * @param <T> 操作的返回值类型
     */
    public interface RetryableOperation<T> {
        /**
         * 执行可重试操作
         * @return 操作的返回值
         * @throws Exception 执行过程中可能会出错 抛出任意异常
         */
        T perform() throws Exception;
    }

    private static final ThreadLocal<RetryTemplate> RETRY_TEMPLATE = ThreadLocal.withInitial(() -> {
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setThrowLastExceptionOnExhausted(false);
        return retryTemplate;
    });

    /**
     * 重试默认次
     * @param operation 可重试操作
     * @param <T> 可重试操作的返回类型
     * @return 可重试操作在指定次数内成功 则返回true 否则返回false
     */
    public static <T> T performRetryableOperation(RetryableOperation<T> operation) {
        return performRetryableOperation(operation, DEFAULT_NUM_RETRIES);
    }

    public static <T> T performRetryableOperation(RetryableOperation<T> operation, final int retryNums) {
        RetryTemplate retryTemplate = RETRY_TEMPLATE.get();
        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(retryNums));

        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(3000L);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        List<String> errMessages = new ArrayList<>(retryNums);
        setRetryTemplateListeners(retryTemplate, errMessages);

        try {
            return retryTemplate.execute(context -> {
                try {
                    return operation.perform();
                } catch (Exception ex) {
                    log.debug("异常信息 : " + ex.getMessage(), ex);
                    throw ex;
                }
            });
        } catch (Exception e) {
            if (e instanceof UnRetryable) {
                throw new UnRetryableException(e.getMessage(), e);
            }
            log.warn("可重试操作无法执行成功：" + e.getMessage(), e);
            RetryOperationFailedException ex = new RetryOperationFailedException(
                    String.format("可重试操作经过%d次重试后仍无法执行成功，最后一次的异常信息为: %s"
                            , retryNums
                            , errMessages.size() > 0 ? errMessages.get(errMessages.size() - 1) : "-"
                            ));
            ex.setErrMessages(errMessages);
            throw ex;
        }
    }

    public static <T> T performRetryableOperationUntilTimeout(RetryableOperation<T> operation, Duration timeout) {
        RetryTemplate retryTemplate = RETRY_TEMPLATE.get();

        TimeoutRetryPolicy timeoutRetryPolicy = new TimeoutRetryPolicy();
        timeoutRetryPolicy.setTimeout(timeout.toMillis());

        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(5L);

        retryTemplate.setRetryPolicy(timeoutRetryPolicy);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        List<String> errMessages = new ArrayList<>(16);
        setRetryTemplateListeners(retryTemplate, errMessages);
        try {
            return retryTemplate.execute(context -> operation.perform());
        } catch (Exception e) {
            if (e instanceof UnRetryable) {
                throw new UnRetryableException(e.getMessage(), e);
            }
            log.warn("可重试操作无法执行成功：" + e.getMessage(), e);
            RetryOperationFailedException ex = new RetryOperationFailedException(
                    String.format("可重试操作经过%d ms已超时"
                            , timeout.toMillis()));
            ex.setErrMessages(errMessages);
            throw ex;
        }
    }

    private static void setRetryTemplateListeners(RetryTemplate retryTemplate, List<String> errMessages) {
        retryTemplate.setListeners(new RetryListener[]{
                new RetryListenerSupport() {
                    @Override
                    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback,
                                                                 Throwable throwable) {
                        String errMsg = throwable.getMessage();
                        log.warn("操作第 {} 次出现异常 {}", context.getRetryCount(), errMsg);
                        errMessages.add(errMsg);
                    }
                }
        });
    }
}
