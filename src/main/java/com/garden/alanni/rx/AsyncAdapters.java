package com.garden.alanni.rx;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * @author 吴宇伦
 */
final class AsyncAdapters {
    public static <T> CompletionStage<T> toCompletion(ListenableFuture<T> future) {
        CompletableFuture<T> completableFuture = new CompletableFuture<>();
        future.addCallback(completableFuture::complete
        , completableFuture::completeExceptionally);
        return completableFuture;
    }

    public static <T> ListenableFuture<T> toListenable(CompletionStage<T> stage) {
        SettableListenableFuture<T> future = new SettableListenableFuture<>();
        stage.whenComplete((v, t) ->{
            if (future == null) {
                future.set(v);
            } else {
                future.setException(t);
            }
        });
        return future;
    }
}
