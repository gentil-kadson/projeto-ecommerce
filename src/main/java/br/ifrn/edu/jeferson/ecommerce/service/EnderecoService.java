package br.ifrn.edu.jeferson.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
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

    public void jogueSeClienteJaPossuiEndereco(Cliente cliente) {
        if (enderecoRepository.existsByCliente(cliente)) {
            throw new BusinessException("Este cliente já possui um endereço");
        }
    }

    public void jogueSeClienteNaoPossuiEndereco(Cliente cliente) {
        if (!enderecoRepository.existsByCliente(cliente)) {
            throw new ResourceNotFoundException("Este cliente não possui endereço cadastrado");
        }
    }

    public Endereco buscarEndereco(Long clienteId) {
        return enderecoRepository.findByClienteId(clienteId).orElseThrow(() -> new ResourceNotFoundException("Este cliente não possui um endereço"));
    }

    public Cliente buscarCliente(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Este cliente não existe"));
    }

    public EnderecoResponseDTO salvar(EnderecoRequestDTO enderecoRequestDTO, Long id) {
        Cliente cliente = buscarCliente(id);
        jogueSeClienteJaPossuiEndereco(cliente);
        Endereco endereco = enderecoMapper.toEntity(enderecoRequestDTO);
        
        endereco.setCliente(cliente);
        enderecoRepository.save(endereco);
        return enderecoMapper.toResponseDTO(endereco);
    }

    public EnderecoResponseDTO buscar(Long id) {
        Endereco endereco = buscarEndereco(id);
        return enderecoMapper.toResponseDTO(endereco);
    }

    public void deletar(Long id) {
        Cliente cliente = buscarCliente(id);
        jogueSeClienteNaoPossuiEndereco(cliente);
        Endereco endereco = buscarEndereco(id);
        enderecoRepository.delete(endereco);
    }

    public EnderecoResponseDTO atualizar(EnderecoRequestDTO enderecoRequestDTO, Long id) {
        Endereco endereco = buscarEndereco(id);

        enderecoMapper.updateEntityFromDTO(enderecoRequestDTO, endereco);
        Endereco enderecoAlterado = enderecoRepository.save(endereco);
        return enderecoMapper.toResponseDTO(enderecoAlterado);
    }
}
