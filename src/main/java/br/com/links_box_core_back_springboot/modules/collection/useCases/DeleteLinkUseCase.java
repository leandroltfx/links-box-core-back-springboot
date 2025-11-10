package br.com.links_box_core_back_springboot.modules.collection.useCases;

import br.com.links_box_core_back_springboot.modules.collection.repositories.CollectionRepository;
import br.com.links_box_core_back_springboot.modules.collection.repositories.LinkRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteLinkUseCase {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private LinkRepository linkRepository;

    public void execute(
            UUID userId,
            UUID collectionId,
            UUID linkId
    ) {

        var collectionEntity = collectionRepository
                .findById(collectionId)
                .orElseThrow(EntityNotFoundException::new);

        if (!collectionEntity.getUserId().equals(userId)) {
            throw new EntityNotFoundException();
        }

        linkRepository.deleteById(linkId);
    }

}
