package br.com.emmanoel.biblioteca.repository;

import br.com.emmanoel.biblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {}
