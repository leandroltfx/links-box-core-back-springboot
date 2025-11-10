package br.com.links_box_core_back_springboot.modules.collection.useCases;

import br.com.links_box_core_back_springboot.modules.collection.dtos.UpdateLinkRequestDTO;
import br.com.links_box_core_back_springboot.modules.collection.dtos.UpdateLinkResponseDTO;
import br.com.links_box_core_back_springboot.modules.collection.entities.LinkEntity;
import br.com.links_box_core_back_springboot.modules.collection.mappers.LinkMapper;
import br.com.links_box_core_back_springboot.modules.collection.repositories.CollectionRepository;
import br.com.links_box_core_back_springboot.modules.collection.repositories.LinkRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateLinkUseCase {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private LinkMapper linkMapper;

    public UpdateLinkResponseDTO execute(
            UUID userId,
            UUID collectionId,
            UUID linkId,
            UpdateLinkRequestDTO updateLinkRequestDTO
    ) {

        LinkEntity linkEntity = linkRepository
                .findById(linkId)
                .orElseThrow(EntityNotFoundException::new);

        var collectionEntity = collectionRepository
                .findById(collectionId)
                .orElseThrow(EntityNotFoundException::new);

        if (!collectionEntity.getUserId().equals(userId)) {
            throw new EntityNotFoundException();
        }

        linkEntity.setName(updateLinkRequestDTO.getName());
        linkEntity.setDescription(updateLinkRequestDTO.getDescription());
        linkEntity.setUrl(updateLinkRequestDTO.getUrl());
        linkEntity.setCategory(updateLinkRequestDTO.getCategory());

        var updatedLinkEntity = linkRepository.save(linkEntity);

        var updatedLinkDTO = linkMapper.toDTO(updatedLinkEntity);

        return UpdateLinkResponseDTO
                .builder()
                .message("Link alterado com sucesso!")
                .data(updatedLinkDTO)
                .build();
    }

}
