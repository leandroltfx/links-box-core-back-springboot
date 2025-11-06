package br.com.links_box_core_back_springboot.modules.collection.repositories;

import br.com.links_box_core_back_springboot.modules.collection.entities.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LinkRepository extends JpaRepository<LinkEntity, UUID> {
}
