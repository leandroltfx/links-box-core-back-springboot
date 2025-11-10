package br.com.links_box_core_back_springboot.modules.user.controllers;

import br.com.links_box_core_back_springboot.modules.user.dtos.UserRegistrationRequestDTO;
import br.com.links_box_core_back_springboot.modules.user.dtos.UserRegistrationResponseDTO;
import br.com.links_box_core_back_springboot.modules.user.useCases.UserRegistrationUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRegistrationUseCase userRegistrationUseCase;

    @PostMapping
    public ResponseEntity<UserRegistrationResponseDTO> register(
            @Valid @RequestBody UserRegistrationRequestDTO userRegistrationRequestDTO
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.userRegistrationUseCase.execute(userRegistrationRequestDTO)
        );
    }

}
