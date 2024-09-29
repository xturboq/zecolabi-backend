package com.zecola.project.manager;

import com.zecola.project.common.ErrorCode;
import com.zecola.project.exception.BusinessException;
import org.redisson.api.*;

import javax.annotation.Resource;

public class RedisLimiterManager {

    @Resource
    private RedissonClient redissonClient;

    /**
     * 限流操作
     *
     * @param key 区分不同的限流器，比如不同的用户 id 应该分别统计
     */
    public void doRateLimit(String key) {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        rateLimiter.trySetRate(RateType.OVERALL,2,1, RateIntervalUnit.SECONDS);

        boolean isAcquire = rateLimiter.tryAcquire(1);
        if (!isAcquire){
            throw new BusinessException(ErrorCode.TOO_MANY_REQUEST);
        }
    }

}
