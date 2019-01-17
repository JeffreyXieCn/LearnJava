package com.luv2code.springsecurity.demo.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
  // add a reference to our security data source
  @Autowired private DataSource securityDataSource;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        // .anyRequest()
        // .authenticated()
        .antMatchers("/")
        .hasRole("EMPLOYEE")
        .antMatchers("/leaders/**")
        .hasRole("MANAGER")
        .antMatchers("/systems/**")
        .hasRole("ADMIN")
        .and()
        .formLogin()
        .loginPage("/showMyLoginPage")
        .loginProcessingUrl("/authenticateTheUser")
        .permitAll()
        .and()
        .logout()
        .permitAll()
        .and()
        .exceptionHandling()
        .accessDeniedPage("/access-denied");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // add our users for in memory authentication
    //    UserBuilder users = User.withDefaultPasswordEncoder();
    //    auth.inMemoryAuthentication()
    //        .withUser(users.username("john").password("test123").roles("EMPLOYEE"));
    //    auth.inMemoryAuthentication()
    //        .withUser(users.username("mary").password("test123").roles("EMPLOYEE", "MANAGER"));
    //    auth.inMemoryAuthentication()
    //        .withUser(users.username("susan").password("test123").roles("EMPLOYEE", "ADMIN"));

    // use jdbc authentication ... oh yeah!!!
    auth.jdbcAuthentication().dataSource(securityDataSource);
  }
}
