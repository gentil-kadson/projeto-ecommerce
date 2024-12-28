package br.ifrn.edu.jeferson.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteResponseDTO;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    @Mapping(target = "pedidos", ignore = true)
    Cliente toEntity(ClienteRequestDTO clienteRequestDTO);

    ClienteResponseDTO ToResponseDTO(Cliente cliente);

    List<ClienteResponseDTO> toDTOList(List<Cliente> clientes);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    @Mapping(target = "pedidos", ignore = true)
    void updateEntityFromDTO(ClienteRequestDTO clienteRequestDTO, @MappingTarget Cliente cliente);
}
