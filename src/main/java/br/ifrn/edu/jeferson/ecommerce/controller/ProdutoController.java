package br.ifrn.edu.jeferson.ecommerce.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoPartialRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "API para gerenciamento de produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @Operation(summary = "Cria um novo produto")
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(@RequestBody @Valid ProdutoRequestDTO produtoRequestDTO) {
        return ResponseEntity.ok(produtoService.salvar(produtoRequestDTO));
    }

    @Operation(summary = "Busca um produto")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscar(id));
    }

    @Operation(summary = "Deleta um produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Atualiza um produto")
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDTO produtoRequestDTO) {
        return ResponseEntity.ok(produtoService.atualizar(id, produtoRequestDTO));
    }

    @Operation(summary = "Lista todos os produtos paginados")
    @GetMapping
    public ResponseEntity<Page<ProdutoResponseDTO>> listar(
        @RequestParam(required = false) String nome,
        @RequestParam(required = false) BigDecimal precoMax,
        @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(produtoService.listar(pageable, nome, precoMax));
    }

    @Operation(summary = "Atualiza o estoque de um produto")
    @PatchMapping("/{id}/estoque")
    public ResponseEntity<Void> atualizarEstoque(@PathVariable Long id, @RequestBody @Valid ProdutoPartialRequestDTO produtoPartialRequestDTO) {
        produtoService.atualizarEstoque(id, produtoPartialRequestDTO.getEstoque());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Lista todos os produtos de uma categoria")
    @GetMapping("/categorias/{id}")
    public ResponseEntity<List<ProdutoResponseDTO>> listarPorCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.listarPorCategoria(id));
    }
}
