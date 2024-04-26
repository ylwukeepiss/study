package com.garden.alanni.jvm;

/**
 * @author 吴宇伦
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        // JDK6中，intern()方法的实现，会把首次遇到的字符串实例复制到永久代的字符串常量池中存储
        // 返回的是字符串常量池中该实例的引用

        // JDK7及其之后 字符串常量池已经移到java堆中，intern()方法的实现是返回字符串实例出现的位置的引用

        // 执行结果：
        // JDK6 false
        // JDK7 true
        String str1 = new StringBuilder("computer").append("software").toString();
        System.out.println(str1.intern() == str1);

        // false 因为 java 这个String在执行 {@link sun.misc.Version}就已经存在了
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
