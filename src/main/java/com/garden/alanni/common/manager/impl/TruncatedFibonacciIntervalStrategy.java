package com.garden.alanni.common.manager.impl;

import com.garden.alanni.common.dao.entity.RetryTaskDO;
import com.garden.alanni.common.manager.RetryStrategy;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;

/**
 * @author 吴宇伦
 */
public class TruncatedFibonacciIntervalStrategy implements RetryStrategy {

    public static final String NAME = "truncatedFib";

    /**
     * 以0为基数，最大可用的斐波那契数是第92个（即第93个斐波那契数）
     * <p>注意有这实时： 2^63 = 9,223,372,036,854,775,808, 而第94个斐波那契数是 12,200,160,415,121,876,738</p>
     */
    private static final int INDEX_MAS = 93;

    private static final long DEFAULT_MAX = 30L;

    private static final long[] FIBS = new long[INDEX_MAS];

    static {
        FIBS[0] = 0L;
        FIBS[1] = 1L;
        for (int i = 2; i < INDEX_MAS; ++i) {
            FIBS[i] = FIBS[i - 2] + FIBS[i - 1];
        }
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public ZonedDateTime proposeNextExecutionTime(RetryTaskDO retryTask, Map<String, String> args) {
        // +1是由于第0个斐波那契数为0，如果从0开始取值，那么最开始的那次重试等于总是要求马上重试
        // 因此取值从1开始
        int numRetries = Optional.ofNullable(retryTask.getNumTries()).orElse(0) + 1;
        long max = DEFAULT_MAX;
        int truncatedIndex = 30;
        int index = Math.min(truncatedIndex, numRetries);
        return Optional.ofNullable(retryTask.getExecutionStartTime()).orElse(retryTask.getCreationTime())
                .plusMinutes(Math.min(max, FIBS[index]))
                .truncatedTo(ChronoUnit.MINUTES);
    }
}
