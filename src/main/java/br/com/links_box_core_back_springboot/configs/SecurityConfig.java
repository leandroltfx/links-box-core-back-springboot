package br.com.links_box_core_back_springboot.configs;

import br.com.links_box_core_back_springboot.configs.filters.SecurityRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityRequestFilter securityRequestFilter;

    private static final String[] ALLOWED_URL_LIST = {
            "/user",
            "/login",
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // Desabilitando as configurações padrão do Spring Security
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {

                    // Rotas permitidas sem autenticação
                    auth.requestMatchers(ALLOWED_URL_LIST).permitAll();

                    // Rotas que exigem autenticação
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(securityRequestFilter, BasicAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
