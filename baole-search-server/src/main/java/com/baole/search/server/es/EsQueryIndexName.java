package com.baole.search.server.es;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class EsQueryIndexName {
//public class EsQueryIndexName implements ConfigChangeListener {

    public static final Map<String, String> esQueryIndexNameMap = run();

    private static final String NAMESPACE = "es-index";

    private static final String INDEX_NAME_PREFIX = "saas-mp-goods-";

    private static Map<String, String> run() {

        Map<String, String> map = new ConcurrentHashMap<>();

        map.put("saas-mp-goods-info", "saas-mp-goods-info-01");
        map.put("saas-mp-goods-item", "saas-mp-goods-item-01");
        return map;
    }

//    private static Map<String, String> run() {
//
//        Map<String, String> map = new ConcurrentHashMap<>();
//
//        Config config = ConfigService.getConfig(NAMESPACE);
//        Set<String> propertyNames = config.getPropertyNames();
//        if (CollectionUtils.isEmpty(propertyNames)) {
//            return map;
//        }
//
//        for (String name : propertyNames) {
//
//            if (name != null && name.startsWith(INDEX_NAME_PREFIX)) {
//
//                String indexName = config.getProperty(name, null);
//                if (StringUtils.isBlank(indexName)) {
//                    continue;
//                }
//
//                map.put(name, indexName);
//            }
//        }
//
//        log.info("com.baole.search.server.es.EsQueryIndexName.run esQueryIndexNameMap: {}", JSON.toJSONString(map));
//
//        return map;
//    }

//    @Override
//    @ApolloConfigChangeListener(NAMESPACE)
//    public void onChange(ConfigChangeEvent changeEvent) {
//
//        String namespace = changeEvent.getNamespace();
//        if (!Objects.equals(namespace, NAMESPACE)) {
//            return;
//        }
//
//        Set<String> set = changeEvent.changedKeys();
//        if (CollectionUtils.isEmpty(set)) {
//            return;
//        }
//
//        for (String key : set) {
//
//            if (key != null && key.startsWith(INDEX_NAME_PREFIX)) {
//
//                ConfigChange configChange = changeEvent.getChange(key);
//                if (Objects.equals(PropertyChangeType.DELETED, configChange.getChangeType())) {
//
//                    esQueryIndexNameMap.remove(key);
//                    continue;
//                }
//
//                String indexName = configChange.getNewValue();
//                if (StringUtils.isBlank(indexName)) {
//                    continue;
//                }
//
//                esQueryIndexNameMap.put(key, indexName);
//            }
//        }
//
//        log.info("com.baole.search.server.es.EsQueryIndexName.onChange esQueryIndexNameMap: {}", JSON.toJSONString(esQueryIndexNameMap));
//    }
}
