package com.garden.alanni.thread;

import java.util.concurrent.*;

/**
 * Java实现多线程的方式有三种
 * <p>
 *     定义一个类，继承Thread {@link Thread} 重写run {@link Thread#run()}
 * </p>
 * <p>
 *     定义一个类，实现Runnable {@link Runnable} 重写run {@link Runnable#run()}
 * </p>
 * <p>
 *     定义一个类，实现Callable {@link java.util.concurrent.Callable} 重写call {@link Callable#call()}
 * </p>
 * 总的来说，支持异步回调结果的，都得是后面两种
 * @author 吴宇伦
 */
public class ThreeDiffThread {
    public static void main(String[] args) {
        ImpCallable impCallable = new ImpCallable();
        Future<String> submit = executorService.submit(impCallable);
        try {
            String s = submit.get();
            System.out.println(s);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }

        ImpRunnable impRunnable = new ImpRunnable();
        Future<?> res = executorService.submit(impRunnable);
        try {
            Object o = res.get();
            System.out.println(o.toString());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        ExtThread extThread = new ExtThread();
        extThread.start();
    }

    private static final ExecutorService executorService = new ThreadPoolExecutor(1,
            1,
            0,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(),
            callable -> new Thread(callable, "3-")
            );

    public static class ExtThread extends Thread {
        @Override
        public void run() {
            System.out.println("创建多线程方法一 继承Thread 重写run");
        }
    }

    public static class ImpRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("创建多线程方法二 实现Runnable 重写run");
        }
    }

    public static class ImpCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "创建多线程方法三 实现Callable 重写call";
        }
    }
}
