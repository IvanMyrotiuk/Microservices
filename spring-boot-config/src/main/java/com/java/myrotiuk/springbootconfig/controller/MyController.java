package com.java.myrotiuk.springbootconfig.controller;

import com.java.myrotiuk.springbootconfig.config.DbConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Ivan on 15.03.2021. All rights reserved.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MyController {

    @Value("${my.app.greet}")
    private String greeting;

    @Value("${my.value}")
    private List<String> valueList;

    @Value("#{${my.app.map}}")
    Map<String, String> myMap;

    private final DbConfig dbConfig;

    @GetMapping("/me")
    public ResponseEntity<String> getAbout() {
        String dbConf = dbConfig.getHost() + dbConfig.getPort();
        return ResponseEntity.ok(greeting + " " + valueList + " " + myMap + " " + dbConf);
    }
}
