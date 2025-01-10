package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para requisições de pedido")
public class PedidoRequestDTO {
    @NotEmpty(message = "Um pedido tem que ter pelo menos 1 item")
    @Schema(description = "Lista de itens a serem colocados no pedido")
    List<ItemPedidoRequestDTO> itens;

    @NotNull(message = "O id do cliente não pode estar vazio")
    @Schema(description = "ID do cliente que está fazendo o pedido", example = "1")
    private Long clienteId;
}
