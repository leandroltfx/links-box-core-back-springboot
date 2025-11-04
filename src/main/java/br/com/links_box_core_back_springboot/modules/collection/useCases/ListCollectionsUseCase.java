package br.com.links_box_core_back_springboot.modules.collection.useCases;

import br.com.links_box_core_back_springboot.modules.collection.dtos.CollectionDTO;
import br.com.links_box_core_back_springboot.modules.collection.dtos.ListCollectionResponseDTO;
import br.com.links_box_core_back_springboot.modules.collection.entities.CollectionEntity;
import br.com.links_box_core_back_springboot.modules.collection.mappers.CollectionMapper;
import br.com.links_box_core_back_springboot.modules.collection.repositories.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListCollectionsUseCase {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private CollectionMapper collectionMapper;

    public ListCollectionResponseDTO execute(
            UUID userId,
            int page,
            int size
    ) {

        var collections = getCollectionsPageable(userId, page, size);

        return ListCollectionResponseDTO
                .builder()
                .data(collections)
                .build();
    }

    public PageImpl<CollectionDTO> getCollectionsPageable(
            UUID userId,
            int page,
            int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<CollectionEntity> collectionEntityPage = collectionRepository.findAllByUserId(userId, pageable);

        List<CollectionDTO> collectionDTOList = collectionEntityPage
                .getContent()
                .stream()
                .map(collectionMapper::toDTO)
                .toList();

        return new PageImpl<>(collectionDTOList, pageable, collectionEntityPage.getTotalElements());
    }

}
