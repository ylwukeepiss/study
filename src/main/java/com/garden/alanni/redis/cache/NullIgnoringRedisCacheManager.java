package com.garden.alanni.redis.cache;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.util.Map;

/**
 * @author 吴宇伦
 */
public class NullIgnoringRedisCacheManager extends RedisCacheManager {
    private final RedisCacheWriter redisCacheWriter;
    private final RedisCacheConfiguration defaultCacheConfig;

    public NullIgnoringRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> initialCacheConfigurations, boolean allowInFlightCacheCreation) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations, allowInFlightCacheCreation);
        this.redisCacheWriter = cacheWriter;
        this.defaultCacheConfig = defaultCacheConfiguration;
    }

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfiguration) {
        return new NullIgnoringRedisCache(name, redisCacheWriter, cacheConfiguration == null ? defaultCacheConfig : cacheConfiguration);
    }
}
