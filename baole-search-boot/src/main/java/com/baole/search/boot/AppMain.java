package com.baole.search.boot;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.baole.common.constants.LoggerConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@Slf4j(topic = LoggerConstant.LoggerTopic.SERVICE_LOGGER)
@SpringBootApplication(scanBasePackages = "com.baole")
//@EnableDubbo(scanBasePackages = "com.baole.search.server.api")
//@ImportResource(locations = {
//        "classpath*:META-INF/spring/applicationContext-healthcheck.xml",
//        "classpath*:META-INF/spring/cat/springContext-cat-client-aop-annotation.xml",
//        "classpath*:META-INF/spring/cat/springContext-cat-client-aop.xml"})
//@EnableApolloConfig(value = { "redis", "application", "elasticsearch", "es-index"})
public class AppMain {

    public static void main(String[] args) {

        ConfigurableApplicationContext context;

        try {
            context = SpringApplication.run(AppMain.class);
            if (null != context && context.isRunning()) {
                log.info("------------ baole-search application start success!!! ------------");
            } else {
                log.info("------------ baole-search application start failure!!! ------------");
            }
        } catch (Exception e) {
            log.error("baole-search application start error!!!", e);
        }
    }
}
