package com.garden.alanni.spring.design;

import com.garden.alanni.spring.design.template.DefaultMesProcess;
import com.garden.alanni.spring.design.template.MessageWithNameProcess;

/**
 * @author 吴宇伦
 */
public class DesignModeTest {
    public static void main(String[] args) {
        DefaultMesProcess mesProcess = new MessageWithNameProcess("hello");
        mesProcess.process();
    }
}
