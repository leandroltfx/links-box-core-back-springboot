package br.com.links_box_core_back_springboot.modules.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationResponseDTO {

    private String message;
    private String access_token;
    private Long expires_in;

}
