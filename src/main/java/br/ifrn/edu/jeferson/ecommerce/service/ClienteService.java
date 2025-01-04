package br.ifrn.edu.jeferson.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.ClienteMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    public void jogueSeCpfJaExiste(String cpf, Long clienteId) {
        if (clienteId == null) {
            if (clienteRepository.existsByCpf(cpf)) {
                throw new BusinessException("Já existe um cliente com esse CPF");
            }
        } else {
            if (clienteRepository.existsByCpfAndIdNot(cpf, clienteId)) {
                throw new BusinessException("Já existe um cliente com esse CPF");
            }
        }
    }

    public void jogueSeEmailJaExiste(String email, Long clienteId) {
        if (clienteId == null) {
            if (clienteRepository.existsByEmail(email)) {
                throw new BusinessException("Já existe um cliente com esse email");
            }
        } else {
            if (clienteRepository.existsByEmailAndIdNot(email, clienteId)) {
                throw new BusinessException("Já existe um cliente com esse email");
            }
        }
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO clienteRequestDTO) {
        this.jogueSeCpfJaExiste(clienteRequestDTO.getCpf(), null);
        this.jogueSeEmailJaExiste(clienteRequestDTO.getEmail(), null);

        Cliente cliente = clienteMapper.toEntity(clienteRequestDTO);
        clienteRepository.save(cliente);
        return clienteMapper.toResponseDTO(cliente);
    }

    public List<ClienteResponseDTO> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toDTOList(clientes);
    }

    public ClienteResponseDTO buscarPorId(Long id) {
       Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Este cliente não existe")); 
       return clienteMapper.toResponseDTO(cliente); 
    }

    public void deletar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Este cliente não existe");
        }
        clienteRepository.deleteById(id);
    }

    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Este cliente não existe"));
        this.jogueSeCpfJaExiste(clienteRequestDTO.getCpf(), id);
        this.jogueSeEmailJaExiste(clienteRequestDTO.getEmail(), id);
        
        clienteMapper.updateEntityFromDTO(clienteRequestDTO, cliente);
        Cliente clienteAlterado = clienteRepository.save(cliente);
        return clienteMapper.toResponseDTO(clienteAlterado);
    }
}
