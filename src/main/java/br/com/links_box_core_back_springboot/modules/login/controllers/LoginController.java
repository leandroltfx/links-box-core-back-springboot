package br.com.links_box_core_back_springboot.modules.login.controllers;

import br.com.links_box_core_back_springboot.modules.login.dtos.LoginRequestDTO;
import br.com.links_box_core_back_springboot.modules.login.dtos.LoginResponseDTO;
import br.com.links_box_core_back_springboot.modules.login.useCases.LoginUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO loginRequestDTO
    ) throws AuthenticationException {
        return ResponseEntity.status(HttpStatus.OK).body(this.loginUseCase.execute(loginRequestDTO));
    }

}
