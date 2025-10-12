package br.com.emmanoel.biblioteca.dto;

import br.com.emmanoel.biblioteca.enums.StatusExemplar;

public record ExemplarLivroDto(
        Long id,
        LivroDto livro,
        StatusExemplar status

) {
}
