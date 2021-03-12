package com.java.myrotiuk.moviecatalogservice.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by Ivan on 22.11.2020. All rights reserved.
 */
@Configuration
public class RestConfig {

    @Bean
    /**
     *  Do a discovery in a load balanced way and where we do not call directly particular service.
     *  We just give the hint to eureka server (name of the service which we want to call) to
     *  server discovery which return to eureka client real host and port number for service which will be
     *  called by client eureka service
     */
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        return new RestTemplate(factory);
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

    //for hystrix dashboard
    //    @Bean
//    public ServletRegistrationBean getServlet() {
//        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
//        registrationBean.setLoadOnStartup(1);
//        registrationBean.addUrlMappings("/hystrix.stream");
//        registrationBean.setName("HystrixMetricsStreamServlet");
//        return registrationBean;
//    }
//    @Bean(name = "hystrixRegistrationBean")
//    public ServletRegistrationBean servletRegistrationBean() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(
//                new HystrixMetricsStreamServlet(), "/hystrix.stream");
//        registration.setName("hystrixServlet");
//        registration.setLoadOnStartup(1);
//        return registration;
//    }
}
