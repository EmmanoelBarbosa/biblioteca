package br.com.emmanoel.biblioteca.dto;

public record PessoaDto(
        Long id,
        String nome,
        String cpf,
        String telefone,
        String email

) {
}
