package com.garden.alanni.designModel.watch;

/**
 * @author 吴宇伦
 */
public interface Observer<T> {
    void observe(T event);
}
