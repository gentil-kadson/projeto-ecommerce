package br.ifrn.edu.jeferson.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes/{id}/enderecos")
@Tag(name = "Endereços", description = "API de gerenciamento de endereços de um cliente")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @Operation(summary = "Cria um novo endereço para o cliente")
    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> salvar(@PathVariable Long id, @RequestBody @Valid EnderecoRequestDTO enderecoRequestDTO) {
        return ResponseEntity.ok(enderecoService.salvar(enderecoRequestDTO, id));
    }

    @Operation(summary = "Busca o endereço do cliente")
    @GetMapping
    public ResponseEntity<EnderecoResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(enderecoService.buscar(id));
    }
}
