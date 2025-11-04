package br.com.links_box_core_back_springboot.modules.login.useCases;

import br.com.links_box_core_back_springboot.exceptions.UserNotFoundException;
import br.com.links_box_core_back_springboot.modules.login.dtos.LoginRequestDTO;
import br.com.links_box_core_back_springboot.modules.login.dtos.LoginResponseDTO;
import br.com.links_box_core_back_springboot.modules.user.entities.UserEntity;
import br.com.links_box_core_back_springboot.modules.user.repositories.UserRepository;
import br.com.links_box_core_back_springboot.providers.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class LoginUseCase {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTProvider jwtProvider;

    public LoginResponseDTO execute(
            LoginRequestDTO loginRequestDTO
    ) throws AuthenticationException {

        UserEntity userEntity = this.userRepository
                .findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });

        var passwordMatches = this.passwordEncoder.matches(loginRequestDTO.getPassword(), userEntity.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        return LoginResponseDTO.builder()
                .message("Login realizado com sucesso!")
                .access_token(jwtProvider.generateToken(userEntity.getId()))
                .expires_in(jwtProvider.generateTokenExpirationTime().toEpochMilli())
                .build();
    }

}
