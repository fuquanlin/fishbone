package cn.fql.fishbone.config;

import cn.fql.fishbone.FishBoneConstants;
import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by fuquanlin on 2016/10/27.
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        GuavaCache expireCache = new GuavaCache("local", CacheBuilder.newBuilder()
                .expireAfterWrite(FishBoneConstants.EXPIRE_TIME_MINUTES, TimeUnit.MINUTES)
                .build()
        );
        simpleCacheManager.setCaches(Arrays.asList(expireCache));
        return simpleCacheManager;
    }
}
