package br.ifrn.edu.jeferson.ecommerce.repository;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCpf(String cpf);
    boolean existsByCpfAndIdNot(String cpf, Long id);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
}
