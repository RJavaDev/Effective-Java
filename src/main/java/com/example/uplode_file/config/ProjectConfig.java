package com.example.uplode_file.config;


import com.maxmind.geoip2.DatabaseReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

@Configuration
public class ProjectConfig {
    @Bean
    public DatabaseReader databaseReader() throws IOException {
        File database = new File("C:\\Users\\Ravshanbek\\Downloads\\GeoLite2-City_20230606\\GeoLite2-City_20230606\\GeoLite2-City.mmdb");
        return new DatabaseReader.Builder(database).build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
