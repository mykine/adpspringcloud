package cn.mykine.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by Jo@mykine
 */
@EnableFeignClients
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class SponsorApplication {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        SpringApplication.run(SponsorApplication.class, args);
        long end = System.currentTimeMillis();
        System.out.println("SponsorApplication start!!! cost:"+(end-start)+"ms");
    }
}
