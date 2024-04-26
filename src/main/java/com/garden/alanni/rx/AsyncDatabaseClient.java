package com.garden.alanni.rx;

import java.util.concurrent.CompletionStage;

/**
 * @author 吴宇伦
 */
public interface AsyncDatabaseClient {
    <T> CompletionStage<T> store(CompletionStage<T> stage);
}
