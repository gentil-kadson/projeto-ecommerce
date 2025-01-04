package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
}
