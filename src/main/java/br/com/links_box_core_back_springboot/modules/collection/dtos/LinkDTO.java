package br.com.links_box_core_back_springboot.modules.collection.dtos;

import br.com.links_box_core_back_springboot.modules.collection.enums.LinkCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LinkDTO {

    private UUID id;
    private String name;
    private String description;
    private String url;
    private LinkCategoryEnum category;
    private Boolean isHealthy;
    private LocalDateTime createdAt;

}
