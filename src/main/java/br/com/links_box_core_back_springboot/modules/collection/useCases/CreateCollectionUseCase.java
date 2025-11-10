package br.com.links_box_core_back_springboot.modules.collection.useCases;

import br.com.links_box_core_back_springboot.modules.collection.dtos.CreateCollectionRequestDTO;
import br.com.links_box_core_back_springboot.modules.collection.dtos.CreateCollectionResponseDTO;
import br.com.links_box_core_back_springboot.modules.collection.entities.CollectionEntity;
import br.com.links_box_core_back_springboot.modules.collection.mappers.CollectionMapper;
import br.com.links_box_core_back_springboot.modules.collection.repositories.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateCollectionUseCase {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private CollectionMapper collectionMapper;

    @Autowired
    private ListCollectionsUseCase listCollectionsUseCase;

    public CreateCollectionResponseDTO execute(
            CreateCollectionRequestDTO createCollectionRequestDTO,
            UUID userId
    ) {
        CollectionEntity collectionEntity = collectionMapper.toEntity(createCollectionRequestDTO);
        collectionEntity.setUserId(userId);

        var createdCollectionEntity = this.collectionRepository.save(collectionEntity);

        var createdCollectionDTO = collectionMapper.toDTO(createdCollectionEntity);

        return CreateCollectionResponseDTO
                .builder()
                .message("Coleção cadastrada com sucesso!")
                .data(createdCollectionDTO)
                .build();
    }

}
