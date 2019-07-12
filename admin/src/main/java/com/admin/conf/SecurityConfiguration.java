package com.admin.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http
      .httpBasic()
      .and()
      .authorizeRequests()
      .antMatchers("/index.html", "/").permitAll()
      .anyRequest().hasRole("ADMIN");
/*			.and()
				.csrf().disable();*/
    // @formatter:on
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    // Setting Service to find User in the database.
    // And Setting PassswordEncoder
    auth.userDetailsService()
   // auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

  }
}
