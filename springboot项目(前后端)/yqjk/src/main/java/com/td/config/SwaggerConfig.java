package com.td.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2  //开启swagger2
public class SwaggerConfig {

    //作者信息
    public static final Contact DEFAULT_CONTACT = new Contact("Kari", "http://blog.petchk.cn/", "1580776594@qq.com");


    //配置了Swagger的Docket实例
    //enable是否启动Swagger true启用  false不能启用
    @Bean
    public Docket docket(Environment environment){
        //设置要显示wagger的环境
        Profiles profiles=Profiles.of("dev","test");
        //获取项目中的环境
        Boolean flag=  environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("Kari")
                .enable(flag)
                .select()
                //配置要扫描接口的方式
                .apis(RequestHandlerSelectors.basePackage("com.td.controller"))//基于哪个包下扫描
                //过滤什么路径
                // .paths(PathSelectors.ant("/chk/**"))
                .build();
    }
    //配置Swagger信息
    private ApiInfo apiInfo(){
        return new ApiInfo("Kari的Swagger API文档",
                "冲冲冲",
                "1.0",
                "http://blog.petchk.cn/",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
    }
}