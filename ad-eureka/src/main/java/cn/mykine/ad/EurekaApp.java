package cn.mykine.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaApp {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        SpringApplication.run(EurekaApp.class);
        long end = System.currentTimeMillis();
        System.out.println("EurekaApp start!!! cost:"+(end-start)+"ms");
    }
}
