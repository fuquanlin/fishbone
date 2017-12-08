package cn.fql.lock.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by fuquanlin on 06/12/2017.
 */
@ConfigurationProperties(prefix = "cn.fql.lock")
public class LockConfigurationProperties {
    private String redisRegisterKey = "cn-fql-lock";
    private long redisKeyExpiredTimeMills = 5 * 60 * 1000;

    public String getRedisRegisterKey() {
        return redisRegisterKey;
    }

    public void setRedisRegisterKey(String redisRegisterKey) {
        this.redisRegisterKey = redisRegisterKey;
    }

    public long getRedisKeyExpiredTimeMills() {
        return redisKeyExpiredTimeMills;
    }

    public void setRedisKeyExpiredTimeMills(long redisKeyExpiredTimeMills) {
        this.redisKeyExpiredTimeMills = redisKeyExpiredTimeMills;
    }
}
