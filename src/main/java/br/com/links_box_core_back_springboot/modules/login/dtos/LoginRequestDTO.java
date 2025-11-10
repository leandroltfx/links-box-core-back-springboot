package br.com.links_box_core_back_springboot.modules.login.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDTO {

    @NotBlank(
            message = "Informe o e-mail"
    )
    private String email;

    @NotBlank(
            message = "Informe a senha"
    )
    private String password;

}
