package com.baole.search.boot.config;

import com.alibaba.dubbo.config.*;
import com.baole.common.config.SearchConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class DubboConfig {

    @Resource
    private SearchConfig searchConfig;

    @Bean
    public ApplicationConfig applicationConfig() {

        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("baole-search");

        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(searchConfig.getDubboRegistryAddress());
        registryConfig.setProtocol("zookeeper");
        registryConfig.setCheck(false);

        return registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig() {

        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(searchConfig.getDubboProtocolName());
        protocolConfig.setThreads(searchConfig.getThreads());

        return protocolConfig;
    }

    @Bean
    public MonitorConfig monitorConfig() {

        MonitorConfig monitorConfig = new MonitorConfig();
        monitorConfig.setProtocol(searchConfig.getDubboMonitorProtocol());

        return monitorConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {

        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setCheck(false);
        consumerConfig.setTimeout(30000);

        return consumerConfig;
    }
}
