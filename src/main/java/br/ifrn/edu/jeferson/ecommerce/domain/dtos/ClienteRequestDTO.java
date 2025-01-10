package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para requisições de cliente")
public class ClienteRequestDTO {
    @Schema(description = "Nome do cliente", example = "Máximo")
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Schema(description = "Email do cliente", example = "toddyinho@nescau.com")
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @Schema(description = "CPF do cliente", example = "000.000.000-00")
    @NotBlank(message = "CPF é obrigatório")
    @Pattern(message = "CPF precisa ser no formato XXX.XXX.XXX-XX", regexp = "^[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}$")
    private String cpf;

    @Schema(description = "Número de telefone do cliente", example = "(XX) XXXXX-XXXX")
    @NotBlank(message = "Número de telefone é obrigatório")
    @Pattern(message = "Número precisa ser no formato (XX) XXXXX-XXXX", regexp = "^\\([0-9]{2}\\)\s[0-9]{5}-[0-9]{4}$")
    private String telefone;
}
