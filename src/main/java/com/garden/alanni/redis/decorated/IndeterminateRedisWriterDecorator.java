package com.garden.alanni.redis.decorated;

import org.springframework.data.redis.cache.CacheStatistics;
import org.springframework.data.redis.cache.CacheStatisticsCollector;
import org.springframework.data.redis.cache.RedisCacheWriter;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

/**
 * redis缓存写入装饰器 {@link RedisCacheWriter}
 * <p>
 *     通过设置一个浮动范围，使得过期时间不在同一个时刻 用于解决缓存雪崩问题
 * </p>
 * @author 吴宇伦
 */
public class IndeterminateRedisWriterDecorator implements RedisCacheWriter {

    private final RedisCacheWriter decorated;

    private final double margin;

    public IndeterminateRedisWriterDecorator(RedisCacheWriter decorated) {
        this.decorated = decorated;
        this.margin = 0.0;
    }

    public IndeterminateRedisWriterDecorator(RedisCacheWriter decorated, double margin) {
        this.decorated = decorated;
        this.margin = margin;
    }

    @Override
    public void put(String name, byte[] key, byte[] value, Duration ttl) {
        decorated.put(name, key, value, disturbTtl(ttl));
    }

    @Override
    public byte[] get(String name, byte[] key) {
        return decorated.get(name, key);
    }

    @Override
    public byte[] putIfAbsent(String name, byte[] key, byte[] value, Duration ttl) {
        return decorated.putIfAbsent(name, key, value, disturbTtl(ttl));
    }

    @Override
    public void remove(String name, byte[] key) {
        decorated.remove(name, key);
    }

    @Override
    public void clean(String name, byte[] pattern) {
        decorated.clean(name, pattern);
    }

    @Override
    public void clearStatistics(String name) {
        decorated.clearStatistics(name);
    }

    @Override
    public RedisCacheWriter withStatisticsCollector(CacheStatisticsCollector cacheStatisticsCollector) {
        return decorated.withStatisticsCollector(cacheStatisticsCollector);
    }

    @Override
    public CacheStatistics getCacheStatistics(String cacheName) {
        return decorated.getCacheStatistics(cacheName);
    }

    private Duration disturbTtl(Duration ttl) {
        System.out.println("ttl margin");
        if (isValidTtl(ttl) && margin > 0) {
            double delta = ThreadLocalRandom.current().nextDouble(-margin, margin);
            long millis = ttl.toMillis();
            millis = (long) (millis * (1 + delta));
            if (millis > 0) {
                return Duration.ofMillis(millis);
            }
        }
        return ttl;
    }

    private boolean isValidTtl(Duration ttl) {
        return ttl != null && !ttl.isZero() && !ttl.isNegative();
    }
}
