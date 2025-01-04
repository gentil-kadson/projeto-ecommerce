package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para requisição de endereço")
public class EnderecoResponseDTO {
    @Schema(description = "ID do endereço", example = "1")
    private Long id;

    @Schema(description = "Rua do endereço", example = "Rua Marcelo Magalhães")
    private String rua;

    @Schema(description = "Número do endereço", example = "90")
    private String numero;

    @Schema(description = "Bairro do endereço", example = "Centro")
    private String bairro;

    @Schema(description = "Cidade do seu endereço", example = "Pau dos Ferros")
    private String cidade;

    @Schema(description = "Estado da cidade do seu endereço", example = "RN")
    private String estado;

    @Schema(description = "CEP do endereço", example = "59910-000")
    private String cep;
}
