package br.com.links_box_core_back_springboot.modules.collection.mappers;

import br.com.links_box_core_back_springboot.modules.collection.dtos.CreateLinkRequestDTO;
import br.com.links_box_core_back_springboot.modules.collection.dtos.LinkDTO;
import br.com.links_box_core_back_springboot.modules.collection.entities.LinkEntity;
import org.springframework.stereotype.Service;

@Service
public class LinkMapper {

    public LinkEntity toEntity(
            CreateLinkRequestDTO createLinkRequestDTO
    ) {
        return LinkEntity
                .builder()
                .name(createLinkRequestDTO.getName())
                .description(createLinkRequestDTO.getDescription())
                .url(createLinkRequestDTO.getUrl())
                .category(createLinkRequestDTO.getCategory())
                .build();
    }

    public LinkDTO toDTO(
            LinkEntity linkEntity
    ) {
        return LinkDTO
                .builder()
                .id(linkEntity.getId())
                .name(linkEntity.getName())
                .description(linkEntity.getDescription())
                .url(linkEntity.getUrl())
                .category(linkEntity.getCategory())
                .createdAt(linkEntity.getCreatedAt())
                .build();
    }

}
