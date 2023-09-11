package com.namnd.bookingbe.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@Configuration
@EnableElasticsearchRepositories(basePackages = "com.namnd.bookingbe.repository.elasticsearch")
public class EsConfig extends AbstractElasticsearchConfiguration {


//    @Value("${elasticsearch.host}")
//    private String EsHost;



    @Override
    public RestHighLevelClient elasticsearchClient() {
        // Customize Elasticsearch client settings if needed
        ClientConfiguration
                .builder()
                .connectedTo("localhost:9200")
                .build();
        return RestClients.create(ClientConfiguration.localhost()).rest();
    }

    //Embedded Elasticsearch Server
    /*@Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(nodeBuilder().local(true).node().client());
    }*/
}
