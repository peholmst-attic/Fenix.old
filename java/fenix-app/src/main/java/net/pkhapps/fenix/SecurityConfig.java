package net.pkhapps.fenix;

import net.pkhapps.fenix.core.boundary.rest.support.CurrentFireDepartmentAwareRequestFilter;
import net.pkhapps.fenix.core.security.FenixUserDetailsService;
import net.pkhapps.fenix.core.security.FireDepartmentAwareRoleVoter;
import net.pkhapps.fenix.core.security.context.CurrentFireDepartment;
import net.pkhapps.fenix.core.security.context.FireDepartmentRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Security related system configuration
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    Environment environment;

    @Autowired
    FenixUserDetailsService fenixUserDetailsService;

    @Autowired
    FireDepartmentRetriever fireDepartmentRetriever;

    @Autowired
    CurrentFireDepartment currentFireDepartment;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(fenixUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.csrf().disable(); // TODO Reenable
        // This filter will set and reset the CurrentFireDepartment
        http.addFilterAfter(new CurrentFireDepartmentAwareRequestFilter(fireDepartmentRetriever, currentFireDepartment), SwitchUserFilter.class);
    }

    @Configuration
    @EnableGlobalMethodSecurity(securedEnabled = true)
    public static class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

        @Autowired
        CurrentFireDepartment currentFireDepartment;

        @Override
        protected AccessDecisionManager accessDecisionManager() {
            List<AccessDecisionVoter> decisionVoters = new ArrayList<AccessDecisionVoter>();
            decisionVoters.add(new FireDepartmentAwareRoleVoter(currentFireDepartment));
            decisionVoters.add(new RoleVoter());
            decisionVoters.add(new AuthenticatedVoter());
            return new AffirmativeBased(decisionVoters);
        }

    }
}
