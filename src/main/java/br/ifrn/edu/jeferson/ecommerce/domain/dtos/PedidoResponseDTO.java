package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para respostas com com dados de pedido")
public class PedidoResponseDTO {
    @Schema(description = "ID do pedido", example = "10")
    private Long id;

    @Schema(description = "Data de efetuação do pedido", example = "2025-01-10T15:34:39.641091")
    private LocalDateTime dataPedido;

    @Schema(description = "Valor total do pedido", example = "599.99")
    private BigDecimal valorTotal;

    @Schema(description = "Status do pedido")
    private StatusPedido statusPedido;

    @Schema(description = "ID do cliente que fez o pedido", example = "6")
    private Long clienteId;

    @Schema(description = "Lista os itens que fazem parte do pedido")
    private List<ItemPedidoResponseDTO> itens;
}
