package br.com.links_box_core_back_springboot.modules.collection.useCases;

import br.com.links_box_core_back_springboot.modules.collection.dtos.CreateLinkRequestDTO;
import br.com.links_box_core_back_springboot.modules.collection.dtos.CreateLinkResponseDTO;
import br.com.links_box_core_back_springboot.modules.collection.entities.LinkEntity;
import br.com.links_box_core_back_springboot.modules.collection.mappers.LinkMapper;
import br.com.links_box_core_back_springboot.modules.collection.repositories.CollectionRepository;
import br.com.links_box_core_back_springboot.modules.collection.repositories.LinkRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateLinkUseCase {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private LinkMapper linkMapper;

    public CreateLinkResponseDTO execute(
            UUID userId,
            UUID collectionId,
            CreateLinkRequestDTO createLinkRequestDTO
    ) {
        LinkEntity linkEntity = linkMapper.toEntity(createLinkRequestDTO);

        var collectionEntity = collectionRepository
                .findById(collectionId)
                .orElseThrow(() -> new EntityNotFoundException());

        if (!collectionEntity.getUserId().equals(userId)) {
            throw new EntityNotFoundException();
        }

        linkEntity.setCollectionId(collectionId);

        var createdLinkEntity = linkRepository.save(linkEntity);

        var createdLinkDTO = linkMapper.toDTO(createdLinkEntity);

        return CreateLinkResponseDTO
                .builder()
                .message("Link cadastrado com sucesso!")
                .data(createdLinkDTO)
                .build();
    }

}
