package kr.ac.ajou.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huy on 2016. 11. 17..
 */
@Configuration
@EnableWebSecurity
@ComponentScan("kr.ac.ajou.service")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

// Get Current User
//  https://www.mkyong.com/spring-security/get-current-logged-in-username-in-spring-security/

  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers(
            "/resources/**",
            "/social/**",
            "/users/sign_up",
            "/webjars/**",
            "/static/**",
            "/connect/*").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/users/sign_in")
        .permitAll()
        .and()
        .logout()
        .permitAll();
//            .and().logout().logoutSuccessUrl("/users/sign_in").permitAll()
//            .and().csrf().csrfTokenRepository(csrfTokenRepository())
//            .and().addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
  }

//  private Filter csrfHeaderFilter() {
//    return new OncePerRequestFilter() {
//      @Override
//      protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                      FilterChain filterChain) throws ServletException, IOException {
//        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
//        if (csrf != null) {
//          Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
//          String token = csrf.getToken();
//          if (cookie == null || token != null && !token.equals(cookie.getValue())) {
//            cookie = new Cookie("XSRF-TOKEN", token);
//            cookie.setPath("/social");
//            response.addCookie(cookie);
//          }
//        }
//        filterChain.doFilter(request, response);
//      }
//    };
//  }
//
//  private CsrfTokenRepository csrfTokenRepository() {
//    HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//    repository.setHeaderName("X-XSRF-TOKEN");
//    return repository;
//  }


  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(userDetailsService)
        .passwordEncoder(bCryptPasswordEncoder());
  }
}
