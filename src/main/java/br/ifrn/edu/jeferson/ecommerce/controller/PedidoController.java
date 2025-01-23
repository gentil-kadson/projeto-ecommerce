package br.ifrn.edu.jeferson.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoPartialRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "API para gerenciamento de pedidos e seus itens")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @Operation(summary = "Cria um novo pedido")
    @PostMapping
    public ResponseEntity<PedidoResponseDTO> salvar(@RequestBody @Valid PedidoRequestDTO pedidoRequestDTO) {
        return ResponseEntity.ok(pedidoService.salvar(pedidoRequestDTO));
    }

    @Operation(summary = "Busca um pedido")
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscar(id));
    }

    @Operation(summary = "Lista todos os pedidos paginados")
    @GetMapping
    public ResponseEntity<Page<PedidoResponseDTO>> listar(
        @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(pedidoService.listar(pageable));
    }

    @Operation(summary = "Busca todos os pedidos feitos pelo cliente")
    @GetMapping("/clientes/{id}")
    public ResponseEntity<List<PedidoResponseDTO>> buscarPorCliente(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorCliente(id));
    }

    @Operation(summary = "Atualiza o status de um pedido")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestBody @Valid PedidoPartialRequestDTO pedidoPartialRequestDTO) {
        pedidoService.atualizarStatus(id, pedidoPartialRequestDTO);
        return ResponseEntity.noContent().build();
    }
}
