package br.ifrn.edu.jeferson.ecommerce.specification;

import org.springframework.data.jpa.domain.Specification;

import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;

public class PedidoSpecification {
    public static Specification<Pedido> status(StatusPedido statusPedido) {
        return (root, query, cb) -> 
            statusPedido == null ? 
            null : 
            cb.equal(root.get("statusPedido"), statusPedido);
    }
}
