package net.pkhapps.fenix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.vaadin.spring.stuff.i18n.EnableCompositeMessageSource;
import org.vaadin.spring.stuff.i18n.MessageProvider;
import org.vaadin.spring.stuff.i18n.ResourceBundleMessageProvider;
import org.vaadin.spring.stuff.sidebar.EnableSideBar;

import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Created by peholmst on 2014-06-19.
 */
@EnableAutoConfiguration
@EnableSideBar
@EnableCompositeMessageSource
@EnableJpaRepositories
@ComponentScan
@Configuration
public class Application {

    @Autowired
    ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    MessageProvider applicationMessages() {
        return new ResourceBundleMessageProvider("net.pkhapps.fenix.app.messages");
    }

    @Bean
    Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    MessageInterpolator messageInterpolator() {
        return Validation.buildDefaultValidatorFactory().getMessageInterpolator();
    }
}
