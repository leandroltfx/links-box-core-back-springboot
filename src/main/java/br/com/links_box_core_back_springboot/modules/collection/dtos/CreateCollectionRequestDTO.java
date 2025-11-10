package br.com.links_box_core_back_springboot.modules.collection.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCollectionRequestDTO {

    @Size(min = 3, max = 50, message = "O nome da coleção deve ter entre 3 e 50 caracteres")
    @NotNull(message = "Informe o nome da coleção.")
    private String name;

    @Size(max = 255, message = "A descrição não deve ultrapassar 255 caracteres")
    private String description;

}
