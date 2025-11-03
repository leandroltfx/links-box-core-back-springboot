package br.com.links_box_core_back_springboot.modules.user.mappers;

import br.com.links_box_core_back_springboot.modules.user.dtos.UserRegistrationRequestDTO;
import br.com.links_box_core_back_springboot.modules.user.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserEntity toEntity(UserRegistrationRequestDTO userRegistrationRequestDto) {
        return UserEntity
                .builder()
                .userName(userRegistrationRequestDto.getUserName())
                .email(userRegistrationRequestDto.getEmail())
                .password(userRegistrationRequestDto.getPassword())
                .build();
    }


}
