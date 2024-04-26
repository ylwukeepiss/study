package com.garden.alanni.common.dao.entity;

import java.time.ZonedDateTime;

/**
 * @author 吴宇伦
 */
public class RetryTaskDO {
    private Long id;

    private int operationType;

    private ZonedDateTime executionStartTime;

    private ZonedDateTime creationTime;

    private ZonedDateTime nextExecutionTime;

    private Integer numTries;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }

    public ZonedDateTime getNextExecutionTime() {
        return nextExecutionTime;
    }

    public void setNextExecutionTime(ZonedDateTime nextExecutionTime) {
        this.nextExecutionTime = nextExecutionTime;
    }

    public Integer getNumTries() {
        return numTries;
    }

    public void setNumTries(Integer numTries) {
        this.numTries = numTries;
    }

    public ZonedDateTime getExecutionStartTime() {
        return executionStartTime;
    }

    public void setExecutionStartTime(ZonedDateTime executionStartTime) {
        this.executionStartTime = executionStartTime;
    }

    public ZonedDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
