package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
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
    List<ItemPedidoRequestDTO> itensPedidoRequestDTOs;
}
