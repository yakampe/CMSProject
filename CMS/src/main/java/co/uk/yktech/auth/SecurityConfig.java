package co.uk.yktech.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          .withUser("user1").password("test").roles("USER")
          .and()
          .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
          .and()
          .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
    }
    
    protected void configure(final HttpSecurity http) throws Exception {
        http
        .csrf().disable()
          .authorizeRequests()
          .antMatchers("/*").permitAll()
          .antMatchers("/res/**").permitAll()
          .antMatchers("/blog/**").permitAll()
          .antMatchers("/page/**").permitAll()
          .antMatchers("/user/**").hasRole("USER")
          .antMatchers("/admin/**").hasRole("ADMIN")
          .anyRequest().authenticated()
          .and()
          .formLogin()
          .loginPage("/login")          
          .loginProcessingUrl("/perform_login")
          .defaultSuccessUrl("/admin/home").permitAll()
          .failureUrl("/login?error=true")
          .and()
          .logout()
          .logoutUrl("/logout")
          .logoutSuccessUrl("/?logout")
          .deleteCookies("JSESSIONID");
    }
     
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
    
	
