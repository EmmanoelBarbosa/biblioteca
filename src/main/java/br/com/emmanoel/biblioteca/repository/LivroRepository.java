package br.com.emmanoel.biblioteca.repository;

import br.com.emmanoel.biblioteca.enums.StatusExemplar;
import br.com.emmanoel.biblioteca.model.Autor;
import br.com.emmanoel.biblioteca.model.ExemplarLivro;
import br.com.emmanoel.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
}
