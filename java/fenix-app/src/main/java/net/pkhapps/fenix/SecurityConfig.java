package net.pkhapps.fenix;

import net.pkhapps.fenix.core.entity.FireDepartmentUserRepository;
import net.pkhapps.fenix.core.ldap.FenixLdapUserDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

/**
 * Created by peholmst on 2014-08-02.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    Environment environment;

    @Autowired
    FireDepartmentUserRepository fireDepartmentUserRepository;

    @Bean
    LdapContextSource springSecurityContextSource() {
        final DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource(environment.getProperty("ldap.url"));
        return contextSource;
    }

    @Bean
    FenixLdapUserDetailsMapper fenixLdapUserDetailsMapper() {
        return new FenixLdapUserDetailsMapper(fireDepartmentUserRepository);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        final LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> ldapConfig = auth.ldapAuthentication();
        ldapConfig.contextSource(springSecurityContextSource());
        ldapConfig.userDnPatterns("uid={0},ou=people");
        ldapConfig.groupSearchBase("ou=groups");
        ldapConfig.rolePrefix("LDAP_ROLE_");
        ldapConfig.userDetailsContextMapper(fenixLdapUserDetailsMapper());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // Use Vaadin's CSRF protection
        super.configure(http);
    }
}
