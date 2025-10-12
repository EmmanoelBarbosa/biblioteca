package br.com.emmanoel.biblioteca.dto;

import java.time.LocalDate;

public record EmprestimoDto(
        Long id,
        PessoaDto pessoa,
        ExemplarLivroDto exemplarLivro,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao

) {
}
