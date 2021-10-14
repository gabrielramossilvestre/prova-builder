package br.com.builder.prova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@SpringBootApplication
@EnableCaching
public class ProvaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProvaApplication.class, args);
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {

		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		String host = System.getenv().get("SPRING_REDIS_HOST");
		if (host != null && !host.isEmpty()) {
			redisStandaloneConfiguration.setHostName(host);
		} else {
			redisStandaloneConfiguration.setHostName("localhost");
		}
		redisStandaloneConfiguration.setPort(6379);
		redisStandaloneConfiguration.setDatabase(0);
		JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
		jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));// 60s connection timeout
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory(redisStandaloneConfiguration,
				jedisClientConfiguration.build());

		return jedisConFactory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		System.out.println("--------- Redis Host -------->" + jedisConnectionFactory().getHostName());
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

}
