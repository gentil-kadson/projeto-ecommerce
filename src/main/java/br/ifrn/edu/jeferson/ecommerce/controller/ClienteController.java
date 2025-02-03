package br.ifrn.edu.jeferson.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "API para gerenciamento de clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Cria um novo cliente")
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@RequestBody @Valid ClienteRequestDTO clienteRequestDTO) {
        return ResponseEntity.ok(clienteService.salvar(clienteRequestDTO));
    }

    @Operation(summary = "Lista todos os clientes registrados paginados")
    @GetMapping
    public ResponseEntity<Page<ClienteResponseDTO>> listar(
        @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) { 
        return ResponseEntity.ok(clienteService.listar(pageable));
    }

    @Operation(summary = "Busca um cliente")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @Operation(summary = "Deleta um cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Atualiza um cliente")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ClienteRequestDTO clienteRequestDTO) {
        return ResponseEntity.ok(clienteService.atualizar(id, clienteRequestDTO));
    }

    @Operation(summary = "Lista todos os pedidos feitos pelo cliente")
    @GetMapping("/{id}/pedidos")
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.listarPedidos(id));
    }
}
