package com.example.demo.security.config;

import com.example.demo.appuser.AppUserService;
import com.example.demo.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AppUserService appUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/css/**", "/images/**").permitAll()

                //All requests to be authenticated (because user may want to go to / or /login or /logout or /whatever
                //By requiring them to authenticate for all, we will direct then to /login
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                //By default, spring security will already set the request to /login. Since we map /login from WebMvcConfig to login
                //Thymeleaf will find the loginpage.html from templates/loginpage.html
                //By default the login process is a /login uri with username and password parameters in a form and POST request unless
                //changed using .loginProcessingUrl
                .loginPage("/login")
                //After login, this will call the defaultSuccessUrl endpoint no matter what initial request page they want to find
                // we also map it in WebMvcConfig to force then to always go to index page
                .defaultSuccessUrl("/index",true)
                .permitAll()
                .and()
                //This is to configure logout. By default, the logout url is /logout and a HTTP POST request from indexpage.html
                .logout()
                //Somehow /logout does not work it probably is looping with some spring default implementation
                .logoutSuccessUrl("/logoutsuccess")
                .permitAll()
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(daoAuthenticationProvider());


    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserService);
        provider.setPasswordEncoder(passwordEncoder.bCryptPasswordEncoder());
        return provider;

    }
}

