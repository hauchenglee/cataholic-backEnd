package com.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@EnableJpaAuditing
public class CataholicBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(CataholicBootApplication.class, args);
    }

    @Bean
//    EmbeddedServletContainerCustomizer 1.5.x -> WebServerFactoryCustomizer 2.x.x
//    https://www.baeldung.com/embeddedservletcontainercustomizer-configurableembeddedservletcontainer-spring-boot
//    https://www.baeldung.com/spring-boot-application-configuration
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerCustomizer() {
        return (container -> {
//            https://www.baeldung.com/spring-boot-application-configuration
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
            container.addErrorPages(error404Page);
        });
    }
}

