package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para requisição de endereço")
public class EnderecoRequestDTO {
    @Schema(description = "Rua do endereço", example = "Rua Marcelo Magalhães")
    @NotBlank(message = "Rua é obrigatório")
    private String rua;

    @Schema(description = "Número da moradia", example = "90")
    @NotBlank(message = "Número é obrigatório")
    private String numero;

    @Schema(description = "O bairro onde você mora", example = "Centro")
    @NotBlank(message = "Bairro é obrigatório")
    private String bairro;

    @Schema(description = "Cidade onde você mora", example = "Pau dos Ferros")
    @NotBlank(message = "Cidade é obrigatório")
    private String cidade;

    @Schema(description = "Estado da cidade onde você mora", example = "RN")
    @NotBlank(message = "Estado é obrigatório")
    private String estado;

    @Schema(description = "CEP onde você mora", example = "59910-000")
    @NotBlank(message = "CEP é obrigatório")
    @Pattern(message = "CEP deve estar no formato XXXXX-XXX", regexp = "^[0-9]{5}-[0-9]{3}$")
    private String cep;
}
