package my.virkato.kata312.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain createSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable() //лучше отключить пока
                .authorizeHttpRequests(req -> req
//                        .antMatchers("/admin/**", "/roles/**").hasRole("ADMIN") // разрешено аннотациями методов
//                        .antMatchers("/user").hasAnyRole("USER", "ADMIN") // разрешено следующим фильтром
                        .anyRequest().authenticated()
                )
                .formLogin(req -> req
                        .loginPage("/login").permitAll() // своя форма аутентификации
                )
                .build();
    }

    @Bean
    public PasswordEncoder selectPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
