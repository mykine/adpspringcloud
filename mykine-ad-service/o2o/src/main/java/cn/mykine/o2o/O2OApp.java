package cn.mykine.o2o;

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

@EnableFeignClients
@EnableEurekaClient
@EnableHystrix
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableHystrixDashboard
@SpringBootApplication
public class O2OApp {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        SpringApplication.run(O2OApp.class, args);
        long end = System.currentTimeMillis();
        System.out.println("O2O App start!!! cost:"+(end-start)+"ms");
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {

        return new RestTemplate();
    }
}
