package com.garden.alanni.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * 列表辅助函数
 * @author 吴宇伦
 */
public class ListUitls {
    /**
     * 将列表分成若干批
     * @param list 待划分列表
     * @param batchSize 批次大小
     * @param <T> 列表的值类型
     * @return 分批后的列表流
     */
    public static <T> Stream<List<T>> batching(Collection<T> list, int batchSize) {
        return batching(list, batchSize, false);
    }

    /**
     * 将列表分成若干批 并填充至批次大小
     * @param list
     * @param batchSize
     * @param padToSize
     * @param <T>
     * @return
     */
    public static <T> Stream<List<T>> batching(Collection<T> list, int batchSize, boolean padToSize) {
        return null;
    }
}
