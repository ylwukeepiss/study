package com.garden.alanni.redis.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.garden.alanni.redis.cache.NullIgnoringRedisCacheManager;
import com.garden.alanni.redis.decorated.IndeterminateRedisWriterDecorator;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * redis 通用配置
 * @author 吴宇伦
 */
@ConditionalOnClass(RedisConnectionFactory.class)
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(CacheProperties.class)
@Configuration
public class RedisCacheAutoConfigurer {
    @Resource
    private CacheProperties cacheProperties;

    /**
     * 为了避免缓存集中失效引起的雪崩问题而设置的有效时间变动幅度。在设置ttl的时候，会根据幅度对ttl进行变动
     * 例如 设置有效时间为10mins，则实际有效时间为 10 * [1 + (-0.2, 0.2)] 即结果在区间 (8, 12) min
     */
    private static final double CACHE_TTL_MARGIN = 0.2;

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        IndeterminateRedisWriterDecorator ttlDecorator = new IndeterminateRedisWriterDecorator(redisCacheWriter, CACHE_TTL_MARGIN);
        NullIgnoringRedisCacheManager cacheManager = new NullIgnoringRedisCacheManager(ttlDecorator, redisCacheConfiguration(), Collections.emptyMap(), true);
        cacheManager.setTransactionAware(true);
        return cacheManager;
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        ObjectMapper om = new ObjectMapper();
        om.registerModules(new SimpleModule(), new Jdk8Module(), new JavaTimeModule());
        om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        RedisCacheConfiguration redisConfig = RedisCacheConfiguration
                .defaultCacheConfig();
        redisConfig = redisConfig.serializeValuesWith(RedisSerializationContext
                .SerializationPair
                .fromSerializer(new GenericJackson2JsonRedisSerializer(om)));
        if (redisProperties.getTimeToLive() != null) {
            redisConfig.entryTtl(redisProperties.getTimeToLive());
        }
        if (!redisProperties.isCacheNullValues()) {
            redisConfig = redisConfig.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            redisConfig = redisConfig.disableKeyPrefix();
        }
        return redisConfig;
    }
}
