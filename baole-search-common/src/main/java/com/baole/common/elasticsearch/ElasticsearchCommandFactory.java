package com.baole.common.elasticsearch;

import com.baole.common.constants.LoggerConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = LoggerConstant.LoggerTopic.SERVICE_LOGGER)
public class ElasticsearchCommandFactory {

    @Bean(name = {"elasticsearchCommandByGoods"})
    public ElasticsearchCommand elasticsearchCommandByGoods(@Qualifier("elasticsearchTemplateByGoods") ElasticsearchRestTemplate elasticsearchRestTemplate) {

        return new ElasticsearchCommand(elasticsearchRestTemplate);
    }

    @Bean(name = {"elasticsearchCommandByStock"})
    public ElasticsearchCommand elasticsearchCommandByStock(@Qualifier("elasticsearchTemplateByStock") ElasticsearchRestTemplate elasticsearchRestTemplate) {

        return new ElasticsearchCommand(elasticsearchRestTemplate);
    }
}
