package br.ifrn.edu.jeferson.ecommerce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.ItemPedido;
import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ItemPedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoPartialRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.ItemPedidoMapper;
import br.ifrn.edu.jeferson.ecommerce.mapper.PedidoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.PedidoRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.ProdutoRepository;
import br.ifrn.edu.jeferson.ecommerce.specification.PedidoSpecification;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoMapper itemPedidoMapper;

    public Pedido buscarPedido(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
    }

    public PedidoResponseDTO salvar(PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = pedidoMapper.toEntity(pedidoRequestDTO);

        Cliente cliente = clienteRepository.findById(pedidoRequestDTO.getClienteId()).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        pedido.setCliente(cliente);

        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatusPedido(StatusPedido.AGUARDANDO);

        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ItemPedidoRequestDTO itemDTO : pedidoRequestDTO.getItens()) {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId()).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

            Integer stockQuantity = produto.getEstoque() - itemDTO.getQuantidade();
            if (stockQuantity < 0) {
                throw new BusinessException("Quantidade informada além do estoque ou o produto esgotou.");
            }

            produto.setEstoque(stockQuantity);
            produtoRepository.save(produto);

            ItemPedido itemPedido = itemPedidoMapper.toEntity(itemDTO);
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            
            pedido.getItens().add(itemPedido);
            valorTotal = valorTotal.add(produto.getPreco().multiply(BigDecimal.valueOf(itemDTO.getQuantidade())));
        }

        pedido.setValorTotal(valorTotal);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return pedidoMapper.toResponseDTO(pedidoSalvo);
    }

    public PedidoResponseDTO buscar(Long id) {
        Pedido pedido = buscarPedido(id);
        return pedidoMapper.toResponseDTO(pedido);
    }

    public Page<PedidoResponseDTO> listar(Pageable pageable, StatusPedido statusPedido) {
        Specification<Pedido> specification = Specification.where(PedidoSpecification.status(statusPedido));
        Page<Pedido> pedidosPage = pedidoRepository.findAll(specification, pageable);
        return pedidosPage.map(pedidoMapper::toResponseDTO);
    }

    public List<PedidoResponseDTO> buscarPorCliente(Long id) {
        List<Pedido> pedidos = pedidoRepository.findByClienteId(id);
        return pedidoMapper.toDTOList(pedidos);
    }

    public void atualizarStatus(Long id, PedidoPartialRequestDTO pedidoPartialRequestDTO) {
        Pedido pedido = buscarPedido(id);
        
        if (pedido.getStatusPedido() == pedidoPartialRequestDTO.getStatusPedido()) {
            throw new BusinessException("Não há como atualizar o status do pedido para o que ele já está");
        }

        pedido.setStatusPedido(pedidoPartialRequestDTO.getStatusPedido());
        pedidoRepository.save(pedido);
    }
}
