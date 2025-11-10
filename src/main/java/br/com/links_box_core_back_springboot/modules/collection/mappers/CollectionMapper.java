package br.com.links_box_core_back_springboot.modules.collection.mappers;

import br.com.links_box_core_back_springboot.modules.collection.dtos.CollectionDTO;
import br.com.links_box_core_back_springboot.modules.collection.dtos.CreateCollectionRequestDTO;
import br.com.links_box_core_back_springboot.modules.collection.entities.CollectionEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionMapper {

    public CollectionEntity toEntity(
            CreateCollectionRequestDTO createCollectionRequestDTO
    ) {
        return CollectionEntity
                .builder()
                .name(createCollectionRequestDTO.getName())
                .description(createCollectionRequestDTO.getDescription())
                .build();
    }

    public List<CollectionDTO> toListDTO(
            List<CollectionEntity> collectionEntityList
    ) {
        List<CollectionDTO> collectionDTOList = new ArrayList<>();
        collectionEntityList.forEach(collectionEntity -> {
            collectionDTOList.add(
                    CollectionDTO
                            .builder()
                            .id(collectionEntity.getId())
                            .name(collectionEntity.getName())
                            .description(collectionEntity.getDescription())
                            .createdAt(collectionEntity.getCreatedAt())
                            .build()
            );
        });
        return collectionDTOList;
    }

    public CollectionDTO toDTO(
            CollectionEntity collectionEntity
    ) {
        return CollectionDTO
                .builder()
                .id(collectionEntity.getId())
                .name(collectionEntity.getName())
                .description(collectionEntity.getDescription())
                .createdAt(collectionEntity.getCreatedAt())
                .build();
    }

}
