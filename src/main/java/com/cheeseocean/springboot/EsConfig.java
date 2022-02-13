package com.cheeseocean.springboot;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.apachecommons.CommonsLog;

@Component
@CommonsLog
public class EsConfig implements SmartInitializingSingleton {

    @Autowired
    private RestHighLevelClient client;

    @Override
    public void afterSingletonsInstantiated() {
        File file = new File(System.getProperty("user.home") + "/es/st-oper.json");
        if(file.exists()){
            CreateIndexRequest request = new CreateIndexRequest("st-oper");
            try {
                String source = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
                request.source(source, XContentType.JSON);
                CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
                log.info("es index settings is: " + response.isAcknowledged());
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
