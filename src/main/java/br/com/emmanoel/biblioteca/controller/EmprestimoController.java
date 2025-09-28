package br.com.emmanoel.biblioteca.controller;

import br.com.emmanoel.biblioteca.model.Emprestimo;
import br.com.emmanoel.biblioteca.model.Livro;
import br.com.emmanoel.biblioteca.service.EmprestimoService;
import br.com.emmanoel.biblioteca.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprestimos")
@Tag(name = "Emprestimo", description = "Endpoints para gerenciamento de empréstimos de livros")

public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping("/emprestar")
    @Operation(summary = "Realizar um empréstimo de livro", description = "Permite que uma pessoa empreste um livro, registrando a data do empréstimo.")

    public ResponseEntity<Emprestimo> emprestarLivro(@RequestParam Long pessoaId, @RequestParam Long livroId) {
        Emprestimo novoEmprestimo = emprestimoService.realizarEmprestimo(pessoaId, livroId);
        return ResponseEntity.ok(novoEmprestimo);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Buscar um empréstimo por ID", description = "Retorna os detalhes de um empréstimo específico.")
    public ResponseEntity<Emprestimo> buscarEmprestimoPorId(@PathVariable Long id) {
        return emprestimoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
