package com.sonalake.autodoc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
  private static final String API_KEY = "apiKey";

  @Value("${doc.api.title:}")
  private String title;

  @Value("${doc.api.description:}")
  private String description;

  @Value("${sg.api.version:}")
  private String version;


  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
      .apiInfo(apiInfo())
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.sonalake.autodoc.api"))
      .paths(PathSelectors.any())
      .build()
      .securitySchemes(Collections.singletonList(
        new ApiKey(API_KEY, HttpHeaders.AUTHORIZATION, "header")
      ))
      .securityContexts(Collections.singletonList(apiKeyAuthContextForAllPaths()));
  }

  private SecurityContext apiKeyAuthContextForAllPaths() {
    return SecurityContext.builder()
      .securityReferences(apiKeyAuthScope())
      .forPaths(PathSelectors.ant("/**"))
      .build();
  }

  private List<SecurityReference> apiKeyAuthScope() {
    AuthorizationScope[] authorizationScopes = {new AuthorizationScope("global", "accessEverything")};
    return Collections.singletonList(new SecurityReference(API_KEY, authorizationScopes));
  }


  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
      .title(title)
      .description(description)
      .version(version)
      .build();
  }


}


