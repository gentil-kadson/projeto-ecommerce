package br.ifrn.edu.jeferson.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;


public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Endereco findByClienteId(Long id);
}
