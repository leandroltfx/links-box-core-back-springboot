package br.com.links_box_core_back_springboot.configs.filters;

import br.com.links_box_core_back_springboot.dtos.ResponseErrorDTO;
import br.com.links_box_core_back_springboot.providers.JWTProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null) {
            var token = this.jwtProvider.validateToken(header);

            if (token == null) {
                sendTokenErrorResponse("Token inválido.", response);
                return;
            }

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    token.getSubject(), // token
                    null, // credenciais
                    Collections.emptyList() // lista de autorizações (roles)
            );
            // Inserindo usuário autenticado no contexto de autenticação do spring security
            // dessa forma em todos os fluxos de requisições o Spring consegue informações do usuário
            // e ir validando se ele está autenticado e se tem ou não permissões
            SecurityContextHolder.getContext().setAuthentication(auth);

            request.setAttribute("user_id", token.getSubject());
        } else {
            if ((!request.getRequestURL().toString().contains("user")) && (!request.getRequestURL().toString().contains("login"))) {
                sendTokenErrorResponse("Token não encontrado.", response);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void sendTokenErrorResponse(
            String message,
            HttpServletResponse response
    ) throws IOException {
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO(message);

        // Converte para JSON
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(responseErrorDTO);

        // Define resposta
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(body);
        response.getWriter().flush();
    }
}
