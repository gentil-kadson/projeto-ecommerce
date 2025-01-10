package br.ifrn.edu.jeferson.ecommerce.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.ifrn.edu.jeferson.ecommerce.domain.ItemPedido;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ItemPedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ItemPedidoResponseDTO;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "produtoId", target = "produto.id")
    @Mapping(target = "pedido", ignore = true)
    ItemPedido toEntity(ItemPedidoRequestDTO itemPedidoRequestDTO);

    @Mapping(source = "produto", target = "produto")
    ItemPedidoResponseDTO toResponseDTO(ItemPedido itemPedido);
    List<ItemPedidoResponseDTO> toDTOList(List<ItemPedido> itens);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "produtoId", target = "produto.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pedido", ignore = true)
    void updateEntityFromDTO(ItemPedidoRequestDTO itemPedidoRequestDTO, @MappingTarget ItemPedido itemPedido);
}
