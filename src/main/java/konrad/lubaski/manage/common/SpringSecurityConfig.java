package konrad.lubaski.manage.common;

import konrad.lubaski.manage.account.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    private AccountService accountService;

    public SpringSecurityConfig(AccountService accountService) {
        this.accountService = accountService;
    }

    @Bean
    public SecurityFilterChain configureSecurity(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(customizer -> customizer.disable())
                .headers(customizer -> customizer.disable())
                .authorizeHttpRequests(customizer -> customizer
                        .requestMatchers("/hello").authenticated()
                        .anyRequest().permitAll())
                .userDetailsService(accountService)
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
//adnotacja component mowi Springowi żeby stworzył obiekt danej klasu i nim zarządzał i taki obiekt nazywa się Bean, innym sposobem utworzenia bean jest adnotacja Bean na metodzie