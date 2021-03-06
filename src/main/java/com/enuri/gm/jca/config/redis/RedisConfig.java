package com.enuri.gm.jca.config.redis;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
      RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
      redisStandaloneConfiguration.setHostName(host);
      redisStandaloneConfiguration.setPort(port);
      LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
      return connectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
      RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
      redisTemplate.setKeySerializer(new StringRedisSerializer());
      redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
      redisTemplate.setConnectionFactory(connectionFactory);
      return redisTemplate;
    }

	@Bean
	public RedisCacheManager gsonCacheManager(RedisConnectionFactory cf, ResourceLoader rl) { // json?????? ??? ??????(detache??? ????????? entity???- ????????????, ... ???)
		RedisCacheManagerBuilder builder= RedisCacheManagerBuilder.fromConnectionFactory(cf);
		RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig(rl.getClassLoader())
				.disableCachingNullValues()
				.entryTtl(Duration.ofDays(1)) // ?????? ?????? 1??? ??????
				.computePrefixWith(CacheKeyPrefix.simple())
				.serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())); // json???????????? value ??????. // ???????????? ???????????? ????????? ?????????

		return builder.cacheDefaults(configuration)
				.withInitialCacheConfigurations(getTtlMap(configuration))
				.build();
	}

	// ???????????? ttl ????????? ??????
	private Map<String, RedisCacheConfiguration> getTtlMap(RedisCacheConfiguration configuration) {
		Map<String, RedisCacheConfiguration> map = new HashMap<>();
		Duration d1h= Duration.ofHours(1);	// 1??????
		map.put("listCategory", configuration.entryTtl(d1h));

		return map;
	}
}
