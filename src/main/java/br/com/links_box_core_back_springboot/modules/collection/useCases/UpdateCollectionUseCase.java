package br.com.links_box_core_back_springboot.modules.collection.useCases;

import br.com.links_box_core_back_springboot.modules.collection.dtos.UpdateCollectionRequestDTO;
import br.com.links_box_core_back_springboot.modules.collection.dtos.UpdateCollectionResponseDTO;
import br.com.links_box_core_back_springboot.modules.collection.entities.CollectionEntity;
import br.com.links_box_core_back_springboot.modules.collection.mappers.CollectionMapper;
import br.com.links_box_core_back_springboot.modules.collection.repositories.CollectionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateCollectionUseCase {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private ListCollectionsUseCase listCollectionsUseCase;

    @Autowired
    private CollectionMapper collectionMapper;

    public UpdateCollectionResponseDTO execute(
            UUID collectionId,
            UpdateCollectionRequestDTO updateCollectionRequestDTO,
            UUID userId
    ) {

        CollectionEntity collectionEntity = collectionRepository
                .findById(collectionId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Coleção não encontrada.")
                );

        collectionEntity.setName(updateCollectionRequestDTO.getName());
        collectionEntity.setDescription(updateCollectionRequestDTO.getDescription());

        var updatedCollectionEntity = this.collectionRepository.save(collectionEntity);

        var updatedCollectionDTO = this.collectionMapper.toDTO(updatedCollectionEntity);

        return UpdateCollectionResponseDTO
                .builder()
                .message("Coleção alterada com sucesso!")
                .data(updatedCollectionDTO)
                .build();
    }

}
