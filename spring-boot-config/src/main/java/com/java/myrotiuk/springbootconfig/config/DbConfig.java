package com.java.myrotiuk.springbootconfig.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ivan on 15.03.2021. All rights reserved.
 * Create a bean with config that has prefix db and now we may inject it and reuse anywhere
 */
@Configuration
@ConfigurationProperties("db")
@Getter
@Setter
//@RefreshScope of our beans and with spring actuator fetch new config dynamically without redeploying our app
//I think it is not good as all beans scopes where is put this annotation
// will be refreshed with POST command http://localhost:8084/actuator/referesh
public class DbConfig {

    private String host;
    private Integer port;

}
