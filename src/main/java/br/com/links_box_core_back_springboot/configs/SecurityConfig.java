package br.com.links_box_core_back_springboot.configs;

import br.com.links_box_core_back_springboot.configs.filters.SecurityRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityRequestFilter securityRequestFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // Desabilita CSRF (para APIs REST)
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Permite criação de conta
                        .requestMatchers(HttpMethod.POST, "/user").permitAll()
                        // Permite login
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        // Exige autenticação para deletar conta
                        .requestMatchers(HttpMethod.GET, "/user").authenticated()
                        // Qualquer outra rota precisa de autenticação
                        .anyRequest().authenticated()
                )
                // Adiciona o filtro customizado
                .addFilterBefore(securityRequestFilter, BasicAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
