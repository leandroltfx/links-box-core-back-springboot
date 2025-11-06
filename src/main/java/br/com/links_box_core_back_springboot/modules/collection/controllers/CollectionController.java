package br.com.links_box_core_back_springboot.modules.collection.controllers;

import br.com.links_box_core_back_springboot.modules.collection.dtos.*;
import br.com.links_box_core_back_springboot.modules.collection.useCases.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    @Autowired
    private CreateCollectionUseCase createCollectionUseCase;

    @Autowired
    private ListCollectionsUseCase listCollectionsUseCase;

    @Autowired
    private UpdateCollectionUseCase updateCollectionUseCase;

    @Autowired
    private CreateLinkUseCase createLinkUseCase;

    @Autowired
    private ListLinksUseCase listLinksUseCase;

    @Autowired
    private UpdateLinkUseCase updateLinkUseCase;

    @PostMapping
    public ResponseEntity<CreateCollectionResponseDTO> createCollection(
            @Valid @RequestBody CreateCollectionRequestDTO createCollectionRequestDTO,
            HttpServletRequest httpServletRequest
    ) {
        var userId = httpServletRequest.getAttribute("user_id");
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.createCollectionUseCase.execute(
                        createCollectionRequestDTO,
                        UUID.fromString(userId.toString())
                )
        );
    }

    @GetMapping
    public ResponseEntity<ListCollectionResponseDTO> listCollections(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest httpServletRequest
    ) {
        var userId = httpServletRequest.getAttribute("user_id");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(listCollectionsUseCase.execute(UUID.fromString(userId.toString()), page, size));
    }

    @PutMapping("/{collection_id}")
    public ResponseEntity<UpdateCollectionResponseDTO> updateCollection(
            @PathVariable("collection_id") UUID collectionId,
            @Valid @RequestBody UpdateCollectionRequestDTO updateCollectionRequestDTO,
            HttpServletRequest httpServletRequest
    ) {
        var userId = httpServletRequest.getAttribute("user_id");
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.updateCollectionUseCase.execute(
                        collectionId,
                        updateCollectionRequestDTO,
                        UUID.fromString(userId.toString())
                )
        );
    }

    @PostMapping("/{collection_id}/links")
    public ResponseEntity<CreateLinkResponseDTO> createLink(
        @Valid @RequestBody CreateLinkRequestDTO createLinkRequestDTO,
        @PathVariable("collection_id") UUID collectionId,
        HttpServletRequest httpServletRequest
    ) {
        var userId = httpServletRequest.getAttribute("user_id");
        return ResponseEntity.status(HttpStatus.CREATED).body(
                createLinkUseCase.execute(
                        UUID.fromString(userId.toString()),
                        collectionId,
                        createLinkRequestDTO
                )
        );
    }

    @GetMapping("/{collection_id}/links")
    public ResponseEntity<ListLinksResponseDTO> listLinks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable("collection_id") UUID collectionId,
            HttpServletRequest httpServletRequest
    ) {
        var userId = httpServletRequest.getAttribute("user_id");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(listLinksUseCase.execute(UUID.fromString(userId.toString()), collectionId, page, size));
    }

    @PutMapping("/{collection_id}/links/{link_id}")
    public ResponseEntity<UpdateLinkResponseDTO> updateLink(
            @PathVariable("collection_id") UUID collectionId,
            @PathVariable("link_id") UUID linkId,
            @Valid @RequestBody UpdateLinkRequestDTO updateLinkRequestDTO,
            HttpServletRequest httpServletRequest
    ) {
        var userId = httpServletRequest.getAttribute("user_id");
        return ResponseEntity.status(HttpStatus.OK).body(
                this.updateLinkUseCase.execute(
                        UUID.fromString(userId.toString()),
                        collectionId,
                        linkId,
                        updateLinkRequestDTO
                )
        );
    }

}
