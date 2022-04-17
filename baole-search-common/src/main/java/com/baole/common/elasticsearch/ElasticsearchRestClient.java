package com.baole.common.elasticsearch;

import com.baole.common.config.SearchConfig;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @Description:
* @Author: baole.liang
* @Date: 2021/9/29
*/
@Component
public class ElasticsearchRestClient {
    private volatile RestClient restClient;

    @Resource
    private SearchConfig searchConfig;

    public RestClient getRestClient() {
        if (restClient == null) {
            synchronized(ElasticsearchRestClient.class) {
                if (restClient == null) {
                    restClient = initRestClient();
                }
            }
        }
        return restClient;
    }

    private RestClient initRestClient() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(searchConfig.getEsUsernameByGoods(), searchConfig.getEsPasswordByGoods()));
        String[] hostArr = searchConfig.getEsUrlByGoods().split(":");
        List<HttpHost> httpHosts = new ArrayList<>(1);
        httpHosts.add(new HttpHost(hostArr[0],Integer.parseInt(hostArr[1])));
        HttpHost[] strings = new HttpHost[httpHosts.size()];
        HttpHost[] httpHostArr = httpHosts.toArray(strings);

        RestClientBuilder restClientBuilder = RestClient.builder(httpHostArr);
        RestClient restClient = restClientBuilder.setHttpClientConfigCallback(
                httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)).build();

        return restClient;
    }




}
