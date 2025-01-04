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
        return produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
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

    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }
        produtoRepository.deleteById(id);        
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = buscarProduto(id);
        produtoMapper.updateEntityFromDTO(produtoRequestDTO, produto);
        Produto produtoAlterado = produtoRepository.save(produto);
        return produtoMapper.toResponseDTO(produtoAlterado);
    }

    public ProdutoResponseDTO atualizarEstoque(Long id, Integer estoque) {
        Produto produto = buscarProduto(id);
        produto.setEstoque(estoque);
        Produto produtoAlteado = produtoRepository.save(produto);
        return produtoMapper.toResponseDTO(produtoAlteado);
    }
}
