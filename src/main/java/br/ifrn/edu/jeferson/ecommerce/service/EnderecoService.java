package br.ifrn.edu.jeferson.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.EnderecoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;
    
    @Autowired
    private EnderecoMapper enderecoMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    public EnderecoResponseDTO salvar(EnderecoRequestDTO enderecoRequestDTO, Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário com este id não existe"));
        Endereco endereco = enderecoMapper.toEntity(enderecoRequestDTO);
        
        endereco.setCliente(cliente);
        enderecoRepository.save(endereco);
        return enderecoMapper.ToResponseDTO(endereco);
    }

    public EnderecoResponseDTO buscar(Long id) {
        Endereco endereco = enderecoRepository.findByClienteId(id);
        System.out.println("Olha sóh shamone!");
        System.out.println(endereco);
        return enderecoMapper.ToResponseDTO(endereco);
    }
}
