package br.ifrn.edu.jeferson.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;


@Mapper(componentModel = "spring", uses = {ItemPedidoMapper.class})
public interface PedidoMapper {
    @Mapping(source = "clienteId", target = "cliente.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataPedido", ignore = true)
    @Mapping(target = "statusPedido", ignore = true)
    @Mapping(target = "valorTotal", ignore = true)
    @Mapping(target = "itens", ignore = true)
    Pedido toEntity(PedidoRequestDTO pedidoRequestDTO);

    @Mapping(source = "cliente.id", target = "clienteId")
    PedidoResponseDTO toResponseDTO(Pedido pedido);

    List<PedidoResponseDTO> toDTOList(List<Pedido> pedidos);
}
