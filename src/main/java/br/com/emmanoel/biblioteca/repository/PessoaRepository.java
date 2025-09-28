package br.com.emmanoel.biblioteca.repository;

import br.com.emmanoel.biblioteca.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {}
