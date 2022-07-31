package cn.mykine.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringCloudApplication
public class GatewayApp {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        SpringApplication.run(GatewayApp.class);
        long end = System.currentTimeMillis();
        System.out.println("GatewayApp start!!! cost:"+(end-start)+"ms");
    }
}
