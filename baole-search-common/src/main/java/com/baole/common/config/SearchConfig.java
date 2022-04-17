package com.baole.common.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@ToString
public class SearchConfig {

    @Value("${dubbo.registry.address}")
    private String dubboRegistryAddress;

    @Value("${dubbo.protocol.name}")
    private String dubboProtocolName;

    @Value("${dubbo.monitor.protocol}")
    private String dubboMonitorProtocol;

    @Value("${dubbo.protocol.threads:200}")
    private Integer threads;

    @Value("${es.url.goods}")
    private String esUrlByGoods;

    @Value("${es.username.goods}")
    private String esUsernameByGoods;

    @Value("${es.password.goods}")
    private String esPasswordByGoods;


}
