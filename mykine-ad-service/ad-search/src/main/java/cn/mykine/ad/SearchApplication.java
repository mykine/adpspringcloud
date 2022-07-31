package cn.mykine.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Jo@mykine
 */
@EnableFeignClients
@EnableEurekaClient
@EnableHystrix
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableHystrixDashboard
@SpringBootApplication
public class SearchApplication {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        SpringApplication.run(SearchApplication.class, args);
        long end = System.currentTimeMillis();
        System.out.println("SearchApplication start!!! cost:"+(end-start)+"ms");
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {

        return new RestTemplate();
    }
}
