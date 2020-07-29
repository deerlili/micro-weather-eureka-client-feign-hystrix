package com.deerlili.spring.cloud.weather.controller;

import com.deerlili.spring.cloud.weather.service.CityClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    @Autowired
    private CityClient cityClient;

    @GetMapping("/cities")
    @HystrixCommand(fallbackMethod = "errorFallback")
    public String listCity() {
        // 通过Feign客户端来查找
        String listCity = cityClient.listCity();
        return listCity;
    }

    public String errorFallback() {
        return "city data server is down!";
    }
}
