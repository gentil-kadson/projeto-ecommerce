package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para requisições da tabela intermediária entre itens e pedidos")
public class ItemPedidoRequestDTO {
    @NotNull(message = "A quantidade do item não pode ser vazia")
    @Min(value = 1, message = "Um item tem que ter pelo menos 1 unidade")
    @Schema(description = "A quantidade do item a ser adicionado no pedido", example = "3")
    private Integer quantidade;

    @Schema(description = "ID referente ao produto a ser adicionado como item", example = "2")
    @NotNull(message = "É necessário informar a qual produto o item se refere")
    private Long produtoId;
}