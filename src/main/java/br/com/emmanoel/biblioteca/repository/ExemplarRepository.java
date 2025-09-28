package br.com.emmanoel.biblioteca.repository;

import br.com.emmanoel.biblioteca.enums.StatusExemplar;
import br.com.emmanoel.biblioteca.model.ExemplarLivro;
import br.com.emmanoel.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplarRepository extends JpaRepository<ExemplarLivro, Long> {
    long countByLivroAndStatus(Livro livro, StatusExemplar status);
}
