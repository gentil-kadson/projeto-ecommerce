package br.ifrn.edu.jeferson.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.mapper.ProdutoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    public ProdutoResponseDTO salvar(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = produtoMapper.toEntity(produtoRequestDTO);
        produtoRepository.save(produto);
        return produtoMapper.toResponseDTO(produto);
    }
}
