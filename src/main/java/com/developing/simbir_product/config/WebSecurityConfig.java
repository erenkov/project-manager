package com.developing.simbir_product.config;

import com.developing.simbir_product.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/favicon.ico").permitAll()
                    .antMatchers( "/board/create").hasAnyRole("MANAGER", "ADMIN")
                    .antMatchers("/", "/projects", "/board/**", "/profile/**").fullyAuthenticated()
                    .antMatchers("/registration").not().fullyAuthenticated()
                    .antMatchers("/**").hasRole("ADMIN")
                //todo Далее правки от Данила, вместо того что выше, но у нас поменялся функционал,
                // теперь не много другие права доступа у пользователей, нужно разобраться
//                .antMatchers("/registration").not().fullyAuthenticated()
//                .antMatchers("/login", "/", "/board", "/board/filter", "/profile/**", "/projects", "/task/{id}")
//                .fullyAuthenticated()
//                .antMatchers("/board/create", "/task/create", "/teams").hasAnyRole("MANAGER", "ADMIN")
//                .antMatchers("/projects/**", "/releases/**").hasRole("ADMIN")
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .usernameParameter("email").passwordParameter("password")
                    .defaultSuccessUrl("/projects", true)
                    .failureUrl("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/login")
                .and()
                    .csrf()
                    .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}