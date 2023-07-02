package com.namnd.bookingbe.config.redis;


import com.namnd.bookingbe.config.ApplicationProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


@Configuration
@ConditionalOnProperty(value = "application.redis.enable", havingValue = "true")
public class RedisConfig {
    private final ApplicationProperties applicationProperties;

    public RedisConfig(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(applicationProperties.getRedis().getHost());
        factory.setPort(applicationProperties.getRedis().getPort());
        factory.setTimeout(applicationProperties.getRedis().getTimeout());
        factory.getPoolConfig().setMaxIdle(applicationProperties.getRedis().getMaxTotal());
        return factory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
