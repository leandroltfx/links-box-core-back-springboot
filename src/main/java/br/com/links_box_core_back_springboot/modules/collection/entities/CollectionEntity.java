package br.com.links_box_core_back_springboot.modules.collection.entities;

import br.com.links_box_core_back_springboot.modules.user.entities.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "collections")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    // muitas coleções para um usuário
    @ManyToOne()
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    // chave estrangeira
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
