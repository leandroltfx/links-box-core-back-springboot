package br.com.links_box_core_back_springboot.modules.user.useCases;

import br.com.links_box_core_back_springboot.modules.user.dtos.UserProfileResponseDTO;
import br.com.links_box_core_back_springboot.modules.user.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserProfileUseCase {

    @Autowired
    private UserRepository userRepository;

    public UserProfileResponseDTO execute(
            UUID id
    ) {
        var userEntity = this.userRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return UserProfileResponseDTO
                .builder()
                .userName(userEntity.getUserName())
                .email(userEntity.getEmail())
                .createdAt(userEntity.getCreatedAt())
                .build();
    }

}
