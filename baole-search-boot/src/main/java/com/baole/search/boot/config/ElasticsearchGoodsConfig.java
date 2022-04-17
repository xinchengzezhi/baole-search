package com.baole.search.boot.config;

import com.baole.common.config.SearchConfig;
import com.sun.istack.internal.NotNull;
import org.elasticsearch.client.RestHighLevelClient;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.annotation.Resource;
import java.time.Duration;

@Configuration
@EnableElasticsearchRepositories
public class ElasticsearchGoodsConfig extends ElasticsearchConfigurationSupport {

    @Resource
    private SearchConfig searchConfig;

    @Bean(name = {"elasticsearchClientByGoods"})
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(searchConfig.getEsUrlByGoods())
                .withConnectTimeout(Duration.ofSeconds(3))
                .withSocketTimeout(Duration.ofSeconds(3))
                .withBasicAuth(searchConfig.getEsUsernameByGoods(), searchConfig.getEsPasswordByGoods())
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    @Bean(name = {"elasticsearchTemplateByGoods"})
    public ElasticsearchRestTemplate elasticsearchOperations(@NotNull ElasticsearchConverter elasticsearchConverter, @NotNull @Qualifier("elasticsearchClientByGoods") RestHighLevelClient elasticsearchClient) {
        return new ElasticsearchRestTemplate(elasticsearchClient, elasticsearchConverter);
    }


    @Bean(initMethod = "start")
    @ConfigurationProperties("spring.elasticsearch.bboss")
    public BBossESStarter bbossESStarter(){
        return new BBossESStarter();
    }
}
