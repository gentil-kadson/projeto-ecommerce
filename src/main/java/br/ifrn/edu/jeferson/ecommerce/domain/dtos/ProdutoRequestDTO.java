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
    @NotBlank
    private String nome;
    
    @Schema(description = "Descrição do produto", example = "Console PS3 da Sony")
    @NotBlank
    private String descricao;
    
    @Schema(description = "Preço do produto", example = "530")
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = Integer.MAX_VALUE, fraction = 2)
    private BigDecimal preco;

    @Schema(description = "A quantidade de produtos no estoque", example = "10")
    @NotNull
    private Integer estoque;
}
