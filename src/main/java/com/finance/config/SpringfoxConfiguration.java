package com.finance.config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;


@Configuration
public class SpringfoxConfiguration {
	private static final String AUTH_SERVER = "http://localhost:8080";

	@Value("${security.oauth2.client.client-id}")
	private String clientID;
	
	@Value("${security.oauth2.client.client-secret}")
	private String secret;
	
	
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
            .paths(PathSelectors.regex("/api/.*"))
            .build()
            .securitySchemes(Arrays.asList(securityScheme()))
            .securityContexts(Arrays.asList(securityContext()))
    		.apiInfo(apiEndPointsInfo());
    }
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Spring Boot REST API")
            .description("Finance Management REST API")
            .contact(new Contact("Vanessa Campanha", "www.innitisoftware.com.br", "vos.campanha@gmail.com"))
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .version("1.0.0")
            .build();
    }
    
    @Bean
    public SecurityConfiguration security() {
        
        return SecurityConfigurationBuilder.builder()
            .clientId(clientID)
            .clientSecret(secret)
            .scopeSeparator(" ")
            .enableCsrfSupport(false)
            .useBasicAuthenticationWithAccessCodeGrant(false)
            .build();
    }
    
    private SecurityContext securityContext() {
        return SecurityContext.builder()
          .securityReferences(
            Arrays.asList(new SecurityReference("spring_oauth", scopes())))
          .forPaths(PathSelectors.regex("/api/.*"))
          .build();
    }
    
    private SecurityScheme securityScheme() {
        GrantType grantType = new AuthorizationCodeGrantBuilder()
        		.tokenEndpoint(new TokenEndpoint("https://www.googleapis.com/oauth2/v3/token", "access_token"))
        		.tokenRequestEndpoint(
        				new TokenRequestEndpoint("https://accounts.google.com/o/oauth2/auth", clientID, secret))
        					.build();
     
        SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
            .grantTypes(Arrays.asList(grantType))
            .scopes(Arrays.asList(scopes()))
            .build();
        return oauth;
    }
    
    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = { 
          new AuthorizationScope("email", "for read operations"), 
          new AuthorizationScope("profile", "for write operations")};
//          new AuthorizationScope("api", "Access API") };
        return scopes;
    }
    	
}