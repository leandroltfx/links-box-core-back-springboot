package br.com.links_box_core_back_springboot.modules.collection.repositories;

import br.com.links_box_core_back_springboot.modules.collection.entities.CollectionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CollectionRepository extends JpaRepository<CollectionEntity, UUID> {

    Page<CollectionEntity> findAllByUserId(UUID userId, Pageable pageable);

}
