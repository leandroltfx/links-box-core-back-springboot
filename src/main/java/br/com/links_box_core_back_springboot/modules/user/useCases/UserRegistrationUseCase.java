package br.com.links_box_core_back_springboot.modules.user.useCases;

import br.com.links_box_core_back_springboot.exceptions.UserFoundException;
import br.com.links_box_core_back_springboot.modules.user.dtos.UserRegistrationRequestDTO;
import br.com.links_box_core_back_springboot.modules.user.dtos.UserRegistrationResponseDTO;
import br.com.links_box_core_back_springboot.modules.user.entities.UserEntity;
import br.com.links_box_core_back_springboot.modules.user.mappers.UserMapper;
import br.com.links_box_core_back_springboot.modules.user.repositories.UserRepository;
import br.com.links_box_core_back_springboot.providers.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JWTProvider jwtProvider;

    public UserRegistrationResponseDTO execute(UserRegistrationRequestDTO userRegistrationRequestDTO) {

        UserEntity userEntity = userMapper.toEntity(userRegistrationRequestDTO);

        this.userRepository.findByUserName(
                userEntity.getUserName()
        ).ifPresent(user -> {
            throw new UserFoundException();
        });

        this.userRepository.findByEmail(
                userEntity.getEmail()
        ).ifPresent(user -> {
            throw new UserFoundException();
        });

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        var createdUser = this.userRepository.save(userEntity);

        return UserRegistrationResponseDTO
                .builder()
                .access_token(jwtProvider.generateToken(createdUser.getId()))
                .expires_in(jwtProvider.generateTokenExpirationTime().toEpochMilli())
                .message("Usuário cadastrado com sucesso!")
                .build();
    }

}
