package com.github.yuanluoji.mbye.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .build()
                .globalRequestParameters(globalRequestParameters());
//                .securitySchemes(security());
    }

    /**
     * 配置方式1，可以在每个请求上，加一个Authorization的输入框
     *
     * @return
     */
    private List<RequestParameter> globalRequestParameters() {
        RequestParameterBuilder parameterBuilder = new RequestParameterBuilder()
                .in(ParameterType.HEADER)
                .name("Authorization")
                .required(false)
                .query(param -> param.model(model -> model.scalarModel(ScalarType.STRING)));
        return Collections.singletonList(parameterBuilder.build());

    }

    /**
     * 配置方式2,Swagger的Auth按钮
     *
     * @return
     */
    private List<SecurityScheme> security() {
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
        return Collections.singletonList(apiKey);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("mbye api")
                .description("nothing here")
                .version("1.0")
                .build();
    }
}

