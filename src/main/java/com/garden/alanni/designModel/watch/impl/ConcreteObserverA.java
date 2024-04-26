package com.garden.alanni.designModel.watch.impl;

import com.garden.alanni.designModel.watch.Observer;

/**
 * @author 吴宇伦
 */
public class ConcreteObserverA implements Observer<String> {
    @Override
    public void observe(String event) {
        System.out.println("Observer A : " + event);
    }
}
