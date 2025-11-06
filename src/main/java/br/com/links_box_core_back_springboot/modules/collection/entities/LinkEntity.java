package br.com.links_box_core_back_springboot.modules.collection.entities;

import br.com.links_box_core_back_springboot.modules.collection.enums.LinkCategoryEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "links")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    private String url;

    private LinkCategoryEnum category;

    // muitos links para uma coleção
    @ManyToOne()
    @JoinColumn(name = "collection_id", insertable = false, updatable = false)
    private CollectionEntity collection;

    // chave estrangeira
    @Column(name = "collection_id", nullable = false)
    private UUID collectionId;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
