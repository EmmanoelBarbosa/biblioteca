package br.com.emmanoel.biblioteca.repository;

import br.com.emmanoel.biblioteca.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {}
