package com.jhon.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * <p>功能描述</br> Redis 分布式锁</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RedisLock
 * @date 2017/9/18 21:22
 */
@Component
@Slf4j
public class RedisLock {

	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 *
	 * @param key
	 * @param value 当前时间+超时时间
	 * @return
	 */
	public boolean lock(String key,String value){
		if(redisTemplate.opsForValue().setIfAbsent(key,value)){
			return true;
		}

		String currentValue = redisTemplate.opsForValue().get(key);
		// 如果锁过期
		if(!StringUtils.isEmpty(currentValue)
				&& Long.parseLong(currentValue)<System.currentTimeMillis()){

			String oldValue = redisTemplate.opsForValue().getAndSet(key,value);

			if(!StringUtils.isEmpty(oldValue) && oldValue.equalsIgnoreCase(currentValue)){
				return  true;
			}
		}
		return false;
	}

	/**
	 * 解锁
	 * @param key
	 * @param value
	 */
	public void unLock(String key,String value){
		try{
			String currentValue = redisTemplate.opsForValue().get(key);
			if (!StringUtils.isEmpty(currentValue)&&currentValue.equals(value)){
				redisTemplate.opsForValue().getOperations().delete(key);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("【Redis分布式锁】解锁异常，{}",e);
		}
	}
}
