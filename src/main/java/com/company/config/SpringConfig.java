package com.company.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SpringConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // authentication
        auth.inMemoryAuthentication()
                .withUser("client").password("{noop}client").roles("Bank")
                .and()
                .withUser("profile").password("{noop}profilejon").roles("profile")
                .and()
                .withUser("bankjon").password("{noop}bankov").roles("bank");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Authorithtion
        http.authorizeHttpRequests()
                .antMatchers("/catrgory/").permitAll()
                .antMatchers("/admin/").hasAnyRole("admin")
                .antMatchers("/profile/**").hasAnyRole("profile")
                .anyRequest().authenticated()
                .and().httpBasic();

        http.csrf().disable()
                .cors().disable();

        //  .and().formLogin();
    }



}
