package br.ifrn.edu.jeferson.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.ClienteMapper;
import br.ifrn.edu.jeferson.ecommerce.mapper.PedidoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private PedidoMapper pedidoMapper;

    public Cliente buscarCliente(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }

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

    public Page<ClienteResponseDTO> listar(Pageable pageable) {
        Page<Cliente> clientesPage = clienteRepository.findAll(pageable);
        return clientesPage.map(clienteMapper::toResponseDTO);
    }

    public ClienteResponseDTO buscarPorId(Long id) {
       Cliente cliente = buscarCliente(id);
       return clienteMapper.toResponseDTO(cliente); 
    }

    public void deletar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Este cliente não existe");
        }
        Cliente cliente = buscarCliente(id);
        if (!cliente.getPedidos().isEmpty()) {
            throw new BusinessException("Este cliente não pode ser deletado, pois possui pedidos");
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

    public List<PedidoResponseDTO> listarPedidos(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        return pedidoMapper.toDTOList(cliente.getPedidos());
    }
}
