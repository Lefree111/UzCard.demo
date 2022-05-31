package com.company.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("client").password("{noop}client").roles("client")
                .and()
                .withUser("card").password("{noop}card").roles("card")
                .and()
                // authentication
                .withUser("transaction").password("{noop}transaction").roles("bank");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Authorithtion
        http.authorizeHttpRequests()
                .antMatchers("/client/").permitAll()
                .antMatchers("/card/").hasAnyRole("card")
                .antMatchers("/transaction/**").hasAnyRole("bank")
                .anyRequest().authenticated()
                .and().httpBasic();
        http.csrf().disable()
                .cors().disable();
    }
}
