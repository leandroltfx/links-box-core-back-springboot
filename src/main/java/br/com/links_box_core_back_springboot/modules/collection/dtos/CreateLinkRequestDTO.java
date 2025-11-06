package br.com.links_box_core_back_springboot.modules.collection.dtos;

import br.com.links_box_core_back_springboot.modules.collection.enums.LinkCategoryEnum;
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
public class CreateLinkRequestDTO {

    @Size(min = 3, max = 50, message = "O nome do link deve ter entre 3 e 50 caracteres")
    @NotNull(message = "Informe o nome do link.")
    private String name;

    @NotNull(message = "Informe a URL.")
    private String url;

    @NotNull(message = "Informe a categoria.")
    private LinkCategoryEnum category;

    @Size(max = 255, message = "A descrição não deve ultrapassar 255 caracteres")
    private String description;

}
