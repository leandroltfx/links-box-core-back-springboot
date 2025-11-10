package br.com.links_box_core_back_springboot.modules.login.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDTO {

    private String message;
    private String access_token;
    private Long expires_in;

}
