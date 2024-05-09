package com.demo1;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .paths(new Paths().addPathItem("address", new PathItem()))
                .info(new Info()
                        .title("API 메인 타이틀 : 주소 검색 API")
                        .description("API 상세 설명: 외부 API 문서 모음")
                        .version("1.0.0"));
    }

}
