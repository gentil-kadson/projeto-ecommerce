package br.ifrn.edu.jeferson.ecommerce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.ItemPedido;
import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ItemPedidoRequestDTO;
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
                throw new BusinessException("Produto fora de estoque");
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
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
        return pedidoMapper.toResponseDTO(pedido);
    }
}
