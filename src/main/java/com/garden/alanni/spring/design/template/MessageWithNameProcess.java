package com.garden.alanni.spring.design.template;

/**
 * @author 吴宇伦
 */
public class MessageWithNameProcess extends DefaultMesProcess {
    public MessageWithNameProcess(String str) {
        super(str);
    }

    @Override
    public void preProcess(String str) {
        super.str = "pre_" + str;
    }

    @Override
    public void postProcess(String str) {
        super.str = str + "_post";
    }
}
