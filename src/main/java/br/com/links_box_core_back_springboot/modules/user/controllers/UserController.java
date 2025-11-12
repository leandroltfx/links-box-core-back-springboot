package br.com.links_box_core_back_springboot.modules.user.controllers;

import br.com.links_box_core_back_springboot.modules.user.dtos.UserProfileResponseDTO;
import br.com.links_box_core_back_springboot.modules.user.dtos.UserRegistrationRequestDTO;
import br.com.links_box_core_back_springboot.modules.user.dtos.UserRegistrationResponseDTO;
import br.com.links_box_core_back_springboot.modules.user.useCases.UserProfileUseCase;
import br.com.links_box_core_back_springboot.modules.user.useCases.UserRegistrationUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRegistrationUseCase userRegistrationUseCase;

    @Autowired
    private UserProfileUseCase userProfileUseCase;

    @PostMapping
    public ResponseEntity<UserRegistrationResponseDTO> register(
            @Valid @RequestBody UserRegistrationRequestDTO userRegistrationRequestDTO
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.userRegistrationUseCase.execute(userRegistrationRequestDTO)
        );
    }

    @GetMapping
    public ResponseEntity<UserProfileResponseDTO> getUserProfile(
            HttpServletRequest httpServletRequest
    ) {
        var userId = httpServletRequest.getAttribute("user_id");
        return ResponseEntity.status(HttpStatus.OK).body(
                userProfileUseCase.execute(UUID.fromString(userId.toString()))
        );
    }

}
