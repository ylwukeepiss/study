package com.garden.alanni.asm;

/**
 * @author 吴宇伦
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -version
 * java -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=Add.main
 */
public class Add {
    public static void main(String[] args) {
        System.out.println(plus());
    }

    public static int plus() {
        int i,j;
        i = 3;
        j = 2;
        return i + j;
    }
}
