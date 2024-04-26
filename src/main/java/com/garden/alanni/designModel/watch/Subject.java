package com.garden.alanni.designModel.watch;

/**
 * @author 吴宇伦
 */
public interface Subject<T> {
    void registerObserver(Observer<T> observer);

    void unRegisterObserver(Observer<T> observer);

    void notifyObservers(T event);
}
