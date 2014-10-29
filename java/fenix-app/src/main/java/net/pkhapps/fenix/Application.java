package net.pkhapps.fenix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vaadin.spring.stuff.i18n.EnableCompositeMessageSource;
import org.vaadin.spring.stuff.i18n.MessageProvider;
import org.vaadin.spring.stuff.i18n.ResourceBundleMessageProvider;
import org.vaadin.spring.stuff.sidebar.EnableSideBar;

import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by peholmst on 2014-06-19.
 */
@EnableAutoConfiguration
@EnableSideBar
@EnableCompositeMessageSource
@EnableJpaRepositories
@EnableAsync
@ComponentScan
@Configuration
public class Application implements AsyncConfigurer {

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

    @Override
    public Executor getAsyncExecutor() {
        final Executor executor = Executors.newCachedThreadPool();
        return new Executor() {
            @Override
            public void execute(Runnable command) {
                final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                executor.execute(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            SecurityContext ctx = SecurityContextHolder.createEmptyContext();
                            ctx.setAuthentication(authentication);
                            SecurityContextHolder.setContext(ctx);
                            command.run();
                        } finally {
                            SecurityContextHolder.clearContext();
                        }
                    }
                });
            }
        };
    }
}
