package org.example.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("member 관련 API")
                .version("1.0")
                .description("api 관련 설명입니다.");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

}
