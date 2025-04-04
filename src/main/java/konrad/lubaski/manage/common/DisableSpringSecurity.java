package konrad.lubaski.manage.common;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DisableSpringSecurity {

    public DisableSpringSecurity() {
        System.out.println("disable spring security");
    }

    @Bean
    public Scanner createScanner() {
        return new Scanner(System.in);
    }

    @Bean
    public SecurityFilterChain configureSecurity(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(customizer -> customizer.disable())
                .headers(customizer -> customizer.disable())
                .authorizeHttpRequests(customizer -> customizer.anyRequest().permitAll())
                .build();
    }
}
