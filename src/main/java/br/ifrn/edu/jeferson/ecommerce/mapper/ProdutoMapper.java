package br.ifrn.edu.jeferson.ecommerce.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categorias", ignore = true)
    Produto toEntity(ProdutoRequestDTO produtoRequestDTO);

    ProdutoResponseDTO toResponseDTO(Produto produto);

    List<ProdutoResponseDTO> toDTOList(List<Produto> produtos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categorias", ignore = true)
    void updateEntityFromDTO(ProdutoRequestDTO produtoRequestDTO, @MappingTarget Produto produto);
}
