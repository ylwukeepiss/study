package com.garden.alanni;

import com.garden.alanni.config.RedisAutoConfigurer;
import com.garden.alanni.redis.config.RedisCacheAutoConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Collection;

@SpringBootApplication
@Controller
@EnableCaching
public class AlanniApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlanniApplication.class, args);
	}


	@RequestMapping("hello")
	@ResponseBody
	public String hello() {

		Thread.currentThread().notify();
		int x = 60;

		int[] nums = new int[10];
		int target = 0;
		int len = nums.length;
		int mid = len / 2;
		int i = 0, j = 0;



		return "hello world";
	}
}
