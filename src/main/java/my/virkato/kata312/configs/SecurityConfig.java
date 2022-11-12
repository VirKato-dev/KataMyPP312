package my.virkato.kata312.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

//    SuccessUserHandler successUserHandler;
//
//    public SecurityConfig(SuccessUserHandler successUserHandler) {
//        this.successUserHandler = successUserHandler;
//    }

    @Bean
    public SecurityFilterChain createSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable() //лучше отключить пока
                .authorizeHttpRequests(req -> req
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(req -> req
                        .loginPage("/login").permitAll()
//                        .successHandler(successUserHandler)
                )
                .build();
    }

    @Bean
    public PasswordEncoder selectPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
