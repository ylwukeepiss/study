package com.garden.alanni;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@EnableAutoConfiguration
@SpringBootTest
@EnableCaching
class AlanniApplicationTests {

	@Resource
	private RedisTemplate<String, Integer> redisTemplate;

	@Resource
	private RedisCacheManager cm;

	@Test
	void contextLoads() {
		System.out.println("hello alanni");
	}

	@Test
	public void testRedisDecorator() {
		put(5);
		Map<String, RedisCacheConfiguration> cacheConfigurations = cm.getCacheConfigurations();
		System.out.println(cacheConfigurations);

	}

	@Cacheable(value = "temp",  key = "#id")
	public Integer put(Integer id) {
		return id;
	}

	@Test
	public void getCache() {
		Collection<String> cacheNames = cm.getCacheNames();
		System.out.println(cacheNames);
	}

	@Test
	public void getRedis() {
		final String counterKey = "102203891";
		try {
			redisTemplate.execute(new SessionCallback<Void>() {
				@SuppressWarnings({"NullableProblems", "unchecked"})
				@Override
				public Void execute(RedisOperations operations) throws DataAccessException {
					operations.multi();
					operations.opsForValue().set(counterKey, 1);
					operations.expire(counterKey, 5, TimeUnit.SECONDS);
					operations.exec();
					return null;
				}
			});
			Long counterSet = redisTemplate.opsForValue().increment(counterKey);
			System.out.printf("计数器 %s 已更新为：%d%n", counterKey, counterSet);
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("here");
					redisTemplate.opsForValue().set(counterKey, 12);
					System.out.println("here is " + redisTemplate.opsForValue().get(counterKey));
				}
			}).start();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.out.printf(e.getMessage());
			}
			Integer cnt = redisTemplate.opsForValue().get(counterKey);
			System.out.println("main is " + cnt);
		} catch (Exception ex) {
			System.out.printf("Redis处理出现异常。无法写入计数器：%s。相关的组合商品价格确认任务可能出现重复提交的情况：%s", counterKey, ex.getMessage());
		} finally {
			redisTemplate.unwatch();
		}
	}
}
