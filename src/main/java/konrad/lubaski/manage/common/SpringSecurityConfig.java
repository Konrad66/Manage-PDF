package konrad.lubaski.manage.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain configureSecurity(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(customizer -> customizer.disable())
                .headers(customizer -> customizer.disable())
                .authorizeHttpRequests(customizer -> customizer
                        .requestMatchers("/hello").authenticated()
                        .anyRequest().permitAll())
                .build();
    }
}
//adnotacja component mowi Springowi żeby stworzył obiekt danej klasu i nim zarządzał i taki obiekt nazywa się Bean, innym sposobem utworzenia bean jest adnotacja Bean na metodzie