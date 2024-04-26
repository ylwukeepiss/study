package com.garden.alanni.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args： -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @author 吴宇伦
 */
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> oomObjects = new ArrayList<>();
        while (true) {
            oomObjects.add(new OOMObject());
        }
    }
}
