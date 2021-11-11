package com.example.visitorservice.persistence;

import javax.sql.DataSource;
import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
class DatasourceProxyConfig implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof DataSource) {
            return ProxyDataSourceBuilder
                .create((DataSource) bean)
                .name("dataSource-proxy")
                .logQueryBySlf4j(SLF4JLogLevel.DEBUG)
                .asJson()
                .build();
        }

        return bean;
    }
}
