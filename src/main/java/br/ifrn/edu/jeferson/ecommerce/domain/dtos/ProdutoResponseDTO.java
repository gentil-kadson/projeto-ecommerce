package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para resposta com dados de produto")
public class ProdutoResponseDTO {
    @Schema(description = "ID do produto", example = "5")
    private Long id;

    @Schema(description = "Nome do produto", example = "PlayStation 2")
    private String nome;

    @Schema(description = "Descrição do produto", example = "Melhor console de todos")
    private String descricao;

    @Schema(description = "O preço do produto", example = "739.45")
    private BigDecimal preco;

    @Schema(description = "Quantidade desse produto no estoque", example = "2")
    private Integer estoque;

    @Schema(description = "Categorias as quais esse produto pertence")
    private List<CategoriaResponseDTO> categorias;
}
