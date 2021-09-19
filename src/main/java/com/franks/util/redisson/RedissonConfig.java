package com.franks.util.redisson;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

/**
 * redisson配置
 *
 * @author frank
 * @date 2021/9/18 17:53
 */
@Configuration
@EnableAutoConfiguration
public class RedissonConfig {
    @Value("${spring.redisson.server}")
    String[] server;
    @Value("${spring.redisson.expiration}")
    int expiration;
    @Value("${spring.redisson.cluster}")
    boolean cluster;
    @Value("${spring.redisson.connectionPoolSize}")
    int connectionPoolSize;
    @Value("${spring.redisson.connectionMinimumIdleSize}")
    int connectionMinimumIdleSize;
    @Value("${spring.redisson.subscriptionConnectionPoolSize}")
    int subscriptionConnectionPoolSize;
    @Value("${spring.redisson.subscriptionConnectionMinimumIdleSize}")
    int subscriptionConnectionMinimumIdleSize;

    /**
     * redisson配置
     *
     * @return 初始化 RedissonClient
     */
    @Bean
    public RedissonClient redissonClient() {
        URI redisUri = URI.create(server[0]);
        Config config = new Config();
        if (cluster) {
            ClusterServersConfig clusterServersConfig = config
                    .useClusterServers()
                    .setMasterConnectionPoolSize(connectionPoolSize)
                    .setMasterConnectionMinimumIdleSize(connectionMinimumIdleSize)
                    .setSubscriptionConnectionPoolSize(subscriptionConnectionPoolSize)
                    .addNodeAddress(server);

            if (redisUri.getUserInfo() != null) {
                clusterServersConfig.setPassword(redisUri.getUserInfo().substring(redisUri.getUserInfo().indexOf(':') + 1));
            }
        } else {
            SingleServerConfig singleServerConfig = config
                    .useSingleServer()
                    .setConnectionPoolSize(connectionPoolSize)
                    .setConnectionMinimumIdleSize(connectionMinimumIdleSize)
                    .setSubscriptionConnectionPoolSize(subscriptionConnectionPoolSize)
                    .setAddress(server[0]);

            if (redisUri.getUserInfo() != null) {
                singleServerConfig.setPassword(redisUri.getUserInfo().substring(redisUri.getUserInfo().indexOf(':') + 1));
            }
        }
        return Redisson.create(config);
    }
}
