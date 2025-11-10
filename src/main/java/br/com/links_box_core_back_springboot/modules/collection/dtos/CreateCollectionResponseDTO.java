package br.com.links_box_core_back_springboot.modules.collection.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCollectionResponseDTO {

    private String message;
    private CollectionDTO data;

}
