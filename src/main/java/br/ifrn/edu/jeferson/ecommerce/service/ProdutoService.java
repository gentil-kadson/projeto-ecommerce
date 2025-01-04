package br.ifrn.edu.jeferson.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.ProdutoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    Produto buscarProduto(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Este produto não foi encontrado"));
    }

    public ProdutoResponseDTO salvar(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = produtoMapper.toEntity(produtoRequestDTO);
        produtoRepository.save(produto);
        return produtoMapper.toResponseDTO(produto);
    }

    public ProdutoResponseDTO buscar(Long id) {
        Produto produto = buscarProduto(id);
        return produtoMapper.toResponseDTO(produto);
    }
}
