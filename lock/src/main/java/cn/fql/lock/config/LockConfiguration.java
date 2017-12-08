package cn.fql.lock.config;

import cn.fql.lock.registry.LockRegistry;
import cn.fql.lock.registry.redis.RedisLockRegistry;
import cn.fql.lock.service.LockService;
import cn.fql.lock.service.impl.LockServiceImpl;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by fuquanlin on 06/12/2017.
 */
@Configuration
@EnableConfigurationProperties({LockConfigurationProperties.class})
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class LockConfiguration {

//    @Bean
//    RedisConnectionFactory redisConnectionFactory() {
//        JedisConnectionFactory factory = new JedisConnectionFactory();
//        factory.setHostName("localhost");
//        factory.setPort(6379);
//        factory.setUsePool(true);
//        return factory;
//    }

    @Configuration
    //@ConditionalOnClass(RedisConnectionFactory.class)
    @ConditionalOnProperty( value = "lock.enabled", havingValue = "true", matchIfMissing = false)
    static class RedisLockRegistryConfiguration {
        @Bean
        @ConditionalOnMissingBean(LockRegistry.class)
        //@ConditionalOnBean(RedisConnectionFactory.class)
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

    @Bean
    @ConditionalOnMissingBean(LockService.class)
    public LockService lockService(LockRegistry lockRegistry, LockServiceImpl.LockThreadPoolFactory lockThreadPoolFactory) {
        return new LockServiceImpl(lockRegistry, lockThreadPoolFactory);
    }

}
