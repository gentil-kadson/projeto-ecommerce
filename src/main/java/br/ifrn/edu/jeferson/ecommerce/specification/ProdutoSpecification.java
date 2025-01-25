package br.ifrn.edu.jeferson.ecommerce.specification;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import br.ifrn.edu.jeferson.ecommerce.domain.Produto;

public class ProdutoSpecification {
    public static Specification<Produto> comPrecoMax(BigDecimal precoMax) {
        return (root, query, cb) ->
            precoMax == null ? 
            null : 
            cb.lessThanOrEqualTo(root.get("preco"), precoMax);
    }

    public static Specification<Produto> contendoNome(String nome) {
        return (root, query, cb) ->
            nome == null ?
            null :
            cb.like(root.get("nome"), "%" + nome + "%");
    }
}
