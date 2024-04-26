package com.garden.alanni.jvm;

/**
 * @author 吴宇伦
 */
public class JavaVMStackOverFlowError {
    private int stackLength = 1;
     public void stackLength() {
         stackLength++;
         stackLength();
     }

    public static void main(String[] args) {
        JavaVMStackOverFlowError oom = new JavaVMStackOverFlowError();
        try {
            oom.stackLength();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
