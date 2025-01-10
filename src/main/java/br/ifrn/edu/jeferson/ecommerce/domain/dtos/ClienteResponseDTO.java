package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para resposta com dados do cliente")
public class ClienteResponseDTO {
    @Schema(description = "ID do cliente", example = "1")
    private Long id;

    @Schema(description = "Nome do cliente", example = "Máximo")
    private String nome;

    @Schema(description = "Email do cliente", example = "toddynho@nescau.com")
    private String email;

    @Schema(description = "CPF do cliente", example = "000.000.000-00")
    private String cpf;

    @Schema(description = "Telefone do cliente", example = "(XX) XXXXX-XXXX")
    private String telefone;
}
