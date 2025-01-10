package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para resposta com dados de item do pedido")
public class ItemPedidoResponseDTO {
    @Schema(description = "ID do item que está no pedido", example = "1")
    private Long id;

    @Schema(description = "Quantidade do item que está no pedido", example = "10")
    private Integer quantidade;

    @Schema(description = "Dados sobre o produto que é o item")
    private ProdutoResponseDTO produto;
}
