package com.baole.search.server.es;

import com.baole.common.constants.LoggerConstant;
import com.baole.search.dto.es.enums.EsIndexEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
@Slf4j(topic = LoggerConstant.LoggerTopic.SERVICE_LOGGER)
public class EsQueryFactory<C, T> {

    @Resource
    private Map<String, EsQueryIndex<C, T>> esQueryIndexMap;

    /**
     * 根据索引标识获取处理器
     *
     * @param indexCode 索引标识
     * @return 处理器
     */
    public EsQueryIndex<C, T> getByIndexCode(String indexName) {

        String processorName = EsIndexEnum.getProcessorName(indexName);
        if (processorName == null) {
            return null;
        }

        return esQueryIndexMap.get(processorName);
    }
}
