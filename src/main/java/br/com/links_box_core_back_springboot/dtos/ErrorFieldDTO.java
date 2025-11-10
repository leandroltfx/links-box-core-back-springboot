package br.com.links_box_core_back_springboot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorFieldDTO {

    private String field;
    private String message;

}
