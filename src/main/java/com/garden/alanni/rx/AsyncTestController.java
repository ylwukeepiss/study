package com.garden.alanni.rx;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;

/**
 * @author 吴宇伦
 */
@RestController
public class AsyncTestController {
    @RequestMapping
    public ListenableFuture<?> requestData() {
        AsyncRestTemplate httpClient = new AsyncRestTemplate();
        return null;
    }
}
