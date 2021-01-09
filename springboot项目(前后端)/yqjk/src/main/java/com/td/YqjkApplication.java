package com.td;

import com.td.Filter.CrosFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan({"com.td"})
@ComponentScan({"com.td.service", "com.td.pojo","com.td.mongoDao", "com.td.util"})
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class YqjkApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(YqjkApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean registerFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.addUrlPatterns("/*");
        bean.setFilter(new CrosFilter());
        return bean;
    }
}
