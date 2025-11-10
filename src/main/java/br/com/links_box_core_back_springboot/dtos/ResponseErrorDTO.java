package br.com.links_box_core_back_springboot.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseErrorDTO {

    private String message;
    private List<ErrorFieldDTO> details = new ArrayList<>();

    public ResponseErrorDTO(
            String message
    ) {
        this.message = message;
    }

}
