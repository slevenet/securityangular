package com.admin.conf;

import com.admin.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


  private final UserDetailsServiceImpl userDetailsService;

  @Autowired
  public SecurityConfiguration(UserDetailsServiceImpl userDetailsService) {
    this.userDetailsService = userDetailsService;
  }


  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return bCryptPasswordEncoder;
  }
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    // The pages does not require login
    http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
    // For ADMIN only.
    http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");

    // AccessDeniedException will be thrown.
    http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }
}
