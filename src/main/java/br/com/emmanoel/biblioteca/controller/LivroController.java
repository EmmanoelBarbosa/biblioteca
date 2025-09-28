package br.com.emmanoel.biblioteca.controller;

import br.com.emmanoel.biblioteca.enums.StatusExemplar;
import br.com.emmanoel.biblioteca.model.Livro;
import br.com.emmanoel.biblioteca.repository.ExemplarRepository;
import br.com.emmanoel.biblioteca.repository.LivroRepository;
import br.com.emmanoel.biblioteca.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final LivroRepository livroRepository;
    private final ExemplarRepository exemplarRepository;

    private final LivroService livroService;

    public LivroController(LivroRepository livroRepository, ExemplarRepository exemplarRepository, LivroService livroService) {
        this.livroRepository = livroRepository;
        this.exemplarRepository = exemplarRepository;
        this.livroService = livroService;
    }

    @PostMapping
    public Livro criar(@RequestBody @Valid Livro livro) {
        return livroService.salvar(livro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscar(@PathVariable Long id) {
        return livroService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody @Valid Livro livro) {
        return livroService.buscarPorId(id)
                .map(l -> {
                    livro.setId(id);
                    return ResponseEntity.ok(livroService.salvar(livro));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (livroService.buscarPorId(id).isPresent()) {
            livroService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping
    public List<Livro> buscarPorTitulo(@RequestParam(required = false) String titulo) {
        if (titulo != null) {
            return livroRepository.findByTituloContainingIgnoreCase(titulo);
        }
        return livroRepository.findAll();
    }
    @GetMapping("/{id}/exemplares")
    public Map<String, Long> consultarExemplares(@PathVariable Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        Map<String, Long> resultado = new HashMap<>();
        resultado.put("disponiveis", exemplarRepository.countByLivroAndStatus(livro, StatusExemplar.DISPONIVEL));
        resultado.put("emprestados", exemplarRepository.countByLivroAndStatus(livro, StatusExemplar.EMPRESTADO));
        resultado.put("reservados", exemplarRepository.countByLivroAndStatus(livro, StatusExemplar.RESERVADO));
        return resultado;
    }

}

