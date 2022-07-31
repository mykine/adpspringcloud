package cn.mykine.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Created by Jo@mykine
 */
@EnableEurekaClient
@SpringBootApplication
@EnableHystrixDashboard
public class DashboardApplication {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        SpringApplication.run(DashboardApplication.class, args);
        long end = System.currentTimeMillis();
        System.out.println("SearchApplication start!!! cost:"+(end-start)+"ms");
    }
}
