package br.com.links_box_core_back_springboot.modules.collection.mappers;

import br.com.links_box_core_back_springboot.modules.collection.dtos.CreateLinkRequestDTO;
import br.com.links_box_core_back_springboot.modules.collection.dtos.LinkDTO;
import br.com.links_box_core_back_springboot.modules.collection.entities.LinkEntity;
import br.com.links_box_core_back_springboot.modules.collection.services.LinkHealthCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkMapper {

    @Autowired
    private LinkHealthCheck linkHealthCheck;

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
                .isHealthy(linkHealthCheck.isLinkAlive(linkEntity.getUrl()))
                .createdAt(linkEntity.getCreatedAt())
                .build();
    }

}
