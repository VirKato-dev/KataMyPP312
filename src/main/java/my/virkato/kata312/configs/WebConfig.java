package my.virkato.kata312.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebConfig {

    @Bean
    public SecurityFilterChain createSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((req) -> req
                        .antMatchers("/").permitAll()
                        .antMatchers("/user/**").hasRole("USER")
                        .antMatchers("/user/**").hasRole("ADMIN")
                        .antMatchers("/admin/**").hasRole("ADMIN")
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    @Bean
    public PasswordEncoder selectPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
