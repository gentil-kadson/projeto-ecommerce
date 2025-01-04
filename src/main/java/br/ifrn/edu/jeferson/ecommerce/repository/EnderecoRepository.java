package br.ifrn.edu.jeferson.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;


public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByClienteId(Long id);
    boolean existsByCliente(Cliente cliente);
}
