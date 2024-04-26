package com.garden.alanni.jvm;

/**
 * 对象被GC前进行一次自我拯救
 * 每个对象的finalize()方法只会被系统调用一次
 * 这个对象拯救方法，官方不建议用，它的运行代价太高，且不确定性极大
 * 这只是Java诞生之初，为了让C/C++程序员更容易接受Java所做出的一次妥协
 * @author 吴宇伦
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;
    public void isAlive() {
        System.out.println("I am still alive");
    }
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(1000);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("I am dead");
        }

        // 这一段代码遇上一段一样 却拯救失败
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(1000);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("finally, I am dead");
        }
    }
}
