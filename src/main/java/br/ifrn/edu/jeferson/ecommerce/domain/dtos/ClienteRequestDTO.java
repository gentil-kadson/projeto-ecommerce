package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDTO {
    @Schema(description = "Seu nome", example = "Máximo")
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Schema(description = "Seu email principal", example = "toddyinho@nescau.com")
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido", regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @Schema(description = "Seu CPF", example = "000.000.000-00")
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @Schema(description = "Seu número de telefone", example = "(XX) XXXXX-XXXX")
    @NotBlank(message = "Número de telefone é obrigatório")
    private String telefone;
}
