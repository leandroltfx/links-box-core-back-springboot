package br.com.links_box_core_back_springboot.modules.collection.useCases;

import br.com.links_box_core_back_springboot.modules.collection.dtos.LinkDTO;
import br.com.links_box_core_back_springboot.modules.collection.dtos.ListLinksResponseDTO;
import br.com.links_box_core_back_springboot.modules.collection.entities.LinkEntity;
import br.com.links_box_core_back_springboot.modules.collection.mappers.LinkMapper;
import br.com.links_box_core_back_springboot.modules.collection.repositories.CollectionRepository;
import br.com.links_box_core_back_springboot.modules.collection.repositories.LinkRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListLinksUseCase {

    @Autowired
    private LinkMapper linkMapper;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    public ListLinksResponseDTO execute(
            UUID userId,
            UUID collectionId,
            int page,
            int size
    ) {

        var collections = getCollectionsPageable(userId, collectionId, page, size);

        return ListLinksResponseDTO
                .builder()
                .data(collections)
                .build();
    }

    public PageImpl<LinkDTO> getCollectionsPageable(
            UUID userId,
            UUID collectionId,
            int page,
            int size
    ) {

        var collectionEntity = collectionRepository
                .findById(collectionId)
                .orElseThrow(() -> new EntityNotFoundException());

        if (!collectionEntity.getUserId().equals(userId)) {
            throw new EntityNotFoundException();
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<LinkEntity> linkEntityPage = linkRepository.findAllByCollectionId(collectionId, pageable);

        List<LinkDTO> linkDTOList = linkEntityPage
                .getContent()
                .stream()
                .map(linkMapper::toDTO)
                .toList();

        return new PageImpl<>(linkDTOList, pageable, linkEntityPage.getTotalElements());
    }

}
