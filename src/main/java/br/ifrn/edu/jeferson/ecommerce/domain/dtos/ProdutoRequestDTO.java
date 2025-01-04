package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para requisição de produto")
public class ProdutoRequestDTO {
    @Schema(description = "O nome do produto", example = "PlayStation 3 Slim")
    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;
    
    @Schema(description = "Descrição do produto", example = "Console PS3 da Sony")
    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;
    
    @Schema(description = "Preço do produto", example = "530")
    @NotNull(message = "O preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço tem que ser maior do que 0")
    @Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "O limite de valor foi excedido")
    private BigDecimal preco;

    @Schema(description = "A quantidade de produtos no estoque", example = "10")
    @NotNull(message = "A quantidade do produto no estoque é obrigatória")
    private Integer estoque;
}
