package com.zecola.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池配置类
 */
@Configuration
public class ThreadPoolExecutorConfig {
    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        ThreadFactory threadFactory = new ThreadFactory() {
            private int count = 1;

            @Override
            public  Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("线程：" + count++);
                count++;
                return thread;
            }
        };

        // 创建一个新的线程池，线程池核心大小为2，最大线程数为4，
        // 非核心线程空闲时间为100秒，任务队列为阻塞队列，长度为4，使用自定义的线程工厂创建线程
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 100, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4), threadFactory);
        // 返回创建的线程池
        return threadPoolExecutor;
    }
}
