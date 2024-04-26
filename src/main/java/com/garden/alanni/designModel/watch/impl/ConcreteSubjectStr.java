package com.garden.alanni.designModel.watch.impl;

import com.garden.alanni.designModel.watch.Observer;
import com.garden.alanni.designModel.watch.Subject;

import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 吴宇伦
 */
public class ConcreteSubjectStr implements Subject<String> {
    private final Set<Observer<String>> observers = new CopyOnWriteArraySet<>();

    private final AtomicInteger next_thread_id = new AtomicInteger(0);

    private final ExecutorService executorService = new ThreadPoolExecutor(
              8
            , 100
            , 1
            , TimeUnit.MINUTES
            , new LinkedBlockingDeque<>()
            , runnable -> new Thread(runnable, "obsNotify-" + next_thread_id.getAndIncrement()));


    @Override
    public void registerObserver(Observer<String> observer) {
        observers.add(observer);
    }

    @Override
    public void unRegisterObserver(Observer<String> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String event) {
        observers.forEach(o -> executorService
                                .submit(() -> o.observe(event)));
    }
}
