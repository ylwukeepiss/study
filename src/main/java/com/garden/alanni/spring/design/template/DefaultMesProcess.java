package com.garden.alanni.spring.design.template;

/**
 * @author 吴宇伦
 */
public class DefaultMesProcess {
    public String str;

    public DefaultMesProcess(String str) {
        this.str = str;
    }

    public void preProcess(String str) {

    }
    public void postProcess(String str) {

    }
    public void process() {
        preProcess(str);
        postProcess(str);
        System.out.println(this.str);
    }
}
