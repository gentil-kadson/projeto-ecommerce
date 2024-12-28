package br.ifrn.edu.jeferson.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoResponseDTO;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    Endereco toEntity(EnderecoRequestDTO enderecoRequestDTO);

    EnderecoResponseDTO ToResponseDTO(Endereco endereco);

    List<EnderecoResponseDTO> toDTOList(List<Endereco> enderecos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    void updateEntityFromDTO(EnderecoRequestDTO enderecoRequestDTO, @MappingTarget Endereco endereco);
}
