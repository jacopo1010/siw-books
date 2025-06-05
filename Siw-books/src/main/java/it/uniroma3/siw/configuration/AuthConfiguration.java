package it.uniroma3.siw.configuration;
import static it.uniroma3.siw.model.Credenziali.ADMIN_ROLE;
import static it.uniroma3.siw.model.Credenziali.DEFAULT_ROLE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
import java.lang.annotation.Annotation;

@Configuration
@EnableWebSecurity
public class AuthConfiguration {


    @Autowired private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .authoritiesByUsernameQuery("SELECT username, ruolo from credenziali WHERE username=?")
                .usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credenziali WHERE username=?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain configure(final HttpSecurity HttpSecurity) throws Exception{
        HttpSecurity.csrf().and().cors().disable().authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/","/index","register","/css/**","/images/**","favicon.ico").permitAll()
                .requestMatchers(HttpMethod.GET, "/register","/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/register","/login").permitAll()
                .requestMatchers(HttpMethod.GET,"/admin/**").hasAnyAuthority(ADMIN_ROLE)
                .requestMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(ADMIN_ROLE)
                .anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/success",true)
                .failureUrl("/login?error=true").and().logout().logoutSuccessUrl("/").invalidateHttpSession(true)
                .deleteCookies("JSESSIONID").logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .clearAuthentication(true).permitAll();
        return HttpSecurity.build();
    }

}
