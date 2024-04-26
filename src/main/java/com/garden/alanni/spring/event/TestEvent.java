package com.garden.alanni.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author 吴宇伦
 */
public class TestEvent extends ApplicationEvent {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TestEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public void print() {
        System.out.println(this.msg);
    }
}
