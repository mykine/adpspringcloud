package cn.mykine.ad.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Created by Jo@mykine
 * 消息转化器配置，实现java对象与http协议传输的json数据互相转换
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>>
                                                       converters) {

        converters.clear();
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}
