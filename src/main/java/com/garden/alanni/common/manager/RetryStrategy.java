package com.garden.alanni.common.manager;

import com.garden.alanni.common.dao.entity.RetryTaskDO;
import java.time.ZonedDateTime;
import java.util.Map;

/**
 * @author 吴宇伦
 */
public interface RetryStrategy {
    /**
     * 返回重试策略的名称
     * @return 重试策略的名称
     */
    String name();

    /**
     * 规划下一次执行的时间
     * @param retryTask 重试任务
     * @param args 重试额外参数
     * @return  下一次的执行时间。如果已无需继续执行，可返回null
     */
    ZonedDateTime proposeNextExecutionTime(RetryTaskDO retryTask, Map<String, String> args);
}
