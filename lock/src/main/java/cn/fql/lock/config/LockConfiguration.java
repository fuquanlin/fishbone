package cn.fql.lock.config;

import cn.fql.lock.registry.LockRegistry;
import cn.fql.lock.registry.redis.RedisLockRegistry;
import cn.fql.lock.service.impl.LockServiceImpl;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by fuquanlin on 06/12/2017.
 */
@Configuration
@EnableConfigurationProperties({ LockConfigurationProperties.class })
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class LockConfiguration {

    @Configuration
    @ConditionalOnClass(RedisConnectionFactory.class)
    @ConditionalOnProperty(value = "cn.fql.lock.enable", havingValue = "true", matchIfMissing = false)
    static class RedisLockRegistryConfiguration {
        @Bean
        @ConditionalOnMissingBean(LockRegistry.class)
        @ConditionalOnBean(RedisConnectionFactory.class)
        public RedisLockRegistry getRedisRegistry(LockConfigurationProperties configuraProperties,
                                                  RedisConnectionFactory redisConnectionFactory) {
            return new RedisLockRegistry(redisConnectionFactory, configuraProperties.getRedisRegisterKey(),
                    configuraProperties.getRedisKeyExpiredTimeMills());
        }
    }

    @Configuration
    @ConditionalOnMissingBean(LockServiceImpl.LockThreadPoolFactory.class)
    class DefaultLockThreadPoolFactory implements LockServiceImpl.LockThreadPoolFactory {

        @Override
        public ThreadPoolExecutor createThreadPoolExecutor() {
            return new ThreadPoolExecutor(20, 100, 60L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        }

    }

}
