package br.com.emmanoel.biblioteca.dto;

public record LivroDto
        (
        Long id,
        String titulo,
        String editora,
        String anoPublicacao,
        AutorDto autor
)
 {
}
